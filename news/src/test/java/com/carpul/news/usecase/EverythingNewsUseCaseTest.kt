package com.carpul.news.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carpul.base.network.ResultWrapper
import com.carpul.news.TestConstants
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCase
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCaseImpl
import com.carpul.news.repository.NewsRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import retrofit2.Response.error

class EverythingNewsUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var newsRepository: NewsRepository

    @InjectMockKs
    private lateinit var subject: FetchNewsEverythingUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getSuccessEverythingNews() = runBlocking {

        coEvery {
            newsRepository.fetchEverythingNews(TestConstants.keyword)
        } returns ResultWrapper.Success(TestConstants.articles)

        subject.fetch(TestConstants.keyword)

        Assert.assertEquals(
            subject.getLiveData().value,
            FetchNewsEverythingUseCase.State.Success(TestConstants.articles)
        )
    }

    @Test
    fun onGenericErrorGettingEverythingNews() = runBlocking {

        coEvery {
            newsRepository.fetchEverythingNews(TestConstants.keyword)
        } returns ResultWrapper.GenericError(TestConstants.errorCode400, TestConstants.errorMessage)


        subject.fetch(TestConstants.keyword)

        Assert.assertEquals(
            subject.getLiveData().value,
            FetchNewsEverythingUseCase.State.Error(getGenericErrorMessage())
        )
    }

    @Test
    fun onFailureGettingEverythingNews() = runBlocking {

        coEvery {
            newsRepository.fetchEverythingNews(TestConstants.keyword)
        } returns ResultWrapper.NetworkError(
            HttpException(
                error<ResultWrapper.NetworkError>(
                    TestConstants.errorCode404,
                    ResponseBody.create(
                        TestConstants.contentType,
                        TestConstants.responseNotFoundContent
                    )
                )
            )
        )

        subject.fetch(TestConstants.keyword)

        Assert.assertEquals(
            subject.getLiveData().value,
            FetchNewsEverythingUseCase.State.Failure(getFailureMessage())
        )
    }

    private fun getGenericErrorMessage() = TestConstants.genericErrorMessage

    private fun getFailureMessage() = TestConstants.failureMessage
}