package com.carpul.news

import com.carpul.news.domain.entities.ArticleModel
import com.carpul.news.domain.entities.ResponseListArticles
import okhttp3.MediaType

object TestConstants {

    const val keyword = "bitcoin"
    private const val result = 100
    private const val status = "Ok"
    private val listArticles = listOf<ArticleModel>()
    val articles = ResponseListArticles(listArticles, status, result)
    const val errorCode400 = 400
    const val errorCode404 = 404

    const val errorMessage = "Some Error Message"
    const val genericErrorMessage = "Error code: $errorCode400, message: $errorMessage"

    private const val messageError404 = "NetworkError"
    const val responseNotFoundContent = "{\n\"code\": \"$errorCode404\",\n   \"message\": \"$messageError404\"\n}"
    val contentType = MediaType.parse("application/json")

    const val failureMessage = "HTTP $errorCode404 Response.error()"
}