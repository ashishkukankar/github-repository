package com.aditi.githubrepo.viewModel.api

import com.aditi.githubrepo.viewModel.api.GithubRepoData.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    fun GetAllUsers(): Call<MutableList<repoData>>

    @GET("/users/{user_name}")
    fun getUserDetail(@Path("user_name") name:String):Call<User>

    @GET("/users/{user_name}/repos")
    fun getRepositoryList(@Path("user_name") name:String):Call<MutableList<RepositoryList>>

}
