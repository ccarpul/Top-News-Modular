package com.carpul.news.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.carpul.news.SetupCoroutinesForUnitTest
import com.carpul.news.TestConstants
import com.carpul.news.domain.entities.ArticleListModel
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class EverythingNewsViewModelTest {

    @get: Rule
    var coroutinesTestRule = SetupCoroutinesForUnitTest()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @InjectMockKs
    private lateinit var subject: EverythingNewsViewModelImpl

    private val fetchEverythingNewsLiveData: MutableLiveData<FetchNewsEverythingUseCase.State> =
        MutableLiveData()

    @MockK
    private lateinit var observer: Observer<FetchNewsEverythingUseCase.State>

    @RelaxedMockK
    private lateinit var fetchNewsEverythingUseCase: FetchNewsEverythingUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { fetchNewsEverythingUseCase.getLiveData() } answers { fetchEverythingNewsLiveData }

        subject = EverythingNewsViewModelImpl(fetchNewsEverythingUseCase)
        subject.getState().observeForever { observer }
    }

    @After
    fun after() {
        coroutinesTestRule.testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `WHEN there is an attempt to fetch everything news THEN the user sees a loading animation`() = runBlockingTest {

        coEvery {
            fetchNewsEverythingUseCase.fetch(TestConstants.keyword)
        } coAnswers {
            delay(200)
            setArticlesResult(FetchNewsEverythingUseCase.State.Success(TestConstants.articles))
        }

        subject.fetchEverythingNews(TestConstants.keyword)

        coroutinesTestRule.testDispatcher.advanceTimeBy(100)
        Assert.assertEquals(subject.getState().value, EverythingNewsViewModel.State.Loading)
        coroutinesTestRule.testDispatcher.advanceTimeBy(100)
        Assert.assertEquals(
            subject.getState().value,
            EverythingNewsViewModel.State.Success(
                ArticleListModel(
                    TestConstants.articles.articleResponse,
                    TestConstants.articles.totalResults.toString()
                )
            )
        )
    }

    @Test
    fun `GIVEN that an user can fetch everything news WHEN there is an attempt to fetch everything news THEN the user sees a list of everything news`() = runBlocking {

        coEvery {
            fetchNewsEverythingUseCase.fetch(TestConstants.keyword)
        } coAnswers {
            setArticlesResult(FetchNewsEverythingUseCase.State.Success(TestConstants.articles))
        }

        subject.fetchEverythingNews(TestConstants.keyword)

        assert(
            subject.getState().value ==
                    EverythingNewsViewModel.State.Success(
                        ArticleListModel(
                            TestConstants.articles.articleResponse,
                            TestConstants.articles.totalResults.toString()
                        )
                    )
        )
        coVerify { fetchNewsEverythingUseCase.fetch(TestConstants.keyword) }

        Assert.assertNotNull(subject)
    }

    @Test
    fun fetchEverythingNewsGenericError() = runBlocking {

        coEvery {
            fetchNewsEverythingUseCase.fetch(TestConstants.keyword)
        } coAnswers {
            setArticlesResult(FetchNewsEverythingUseCase.State.Error(TestConstants.genericErrorMessage))
        }

        subject.fetchEverythingNews(TestConstants.keyword)

        Assert.assertNotNull(subject)
        assert(
            subject.getState().value ==
                    EverythingNewsViewModel.State.Error(
                        TestConstants.genericErrorMessage
                    )
        )

        coVerify { fetchNewsEverythingUseCase.fetch(TestConstants.keyword) }

    }

    @Test
    fun fetchEverythingNewsFailure() = runBlocking {

        coEvery {
            fetchNewsEverythingUseCase.fetch(TestConstants.keyword)
        } coAnswers {
            setArticlesResult(FetchNewsEverythingUseCase.State.Failure(TestConstants.failureMessage))
        }

        subject.fetchEverythingNews(TestConstants.keyword)

        Assert.assertNotNull(subject)
        assert(
            subject.getState().value ==
                    EverythingNewsViewModel.State.Error(TestConstants.failureMessage)
        )
        coVerify { fetchNewsEverythingUseCase.fetch(TestConstants.keyword) }
    }

    private fun setArticlesResult(result: FetchNewsEverythingUseCase.State) {
        fetchEverythingNewsLiveData.value = result
    }

}