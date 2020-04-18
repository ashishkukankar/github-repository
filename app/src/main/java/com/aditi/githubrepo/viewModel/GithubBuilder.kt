package com.aditi.githubrepo.viewModel

import com.aditi.githubrepo.viewModel.api.ApiService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class GithubBuilder {

companion object {
    fun invoke(): ApiService? {

        try {
            val retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiService::class.java)
        } catch (e: Exception) {
            println("error message=${e.message}")
        }

        return null

    }
}
}


