package com.aditi.githubrepo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aditi.githubrepo.viewModel.api.GithubRepoData
import com.aditi.githubrepo.viewModel.api.GithubRepoData.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoRepository{
    var githubBuilder = GithubBuilder()
    val userLiveData =  MutableLiveData<MutableList<repoData>>()
    val userDetail =  MutableLiveData<User>()
    val repositoryList =  MutableLiveData<MutableList<RepositoryList>>()


    fun getGitRepo():MutableLiveData<MutableList<repoData>>{
        return userLiveData
    }

    fun  getUser():MutableLiveData<User>{
        return userDetail
    }

    fun getRepositorList():MutableLiveData<MutableList<RepositoryList>>{
        return repositoryList
    }



    fun getAllUser(){
         val apiService = githubBuilder.invoke()
        apiService?.GetAllUsers()?.enqueue(object : Callback<MutableList<repoData>> {
            override fun onFailure(
                call: Call<MutableList<repoData>>,
                t: Throwable) {
                println("Error=$t")
             }

            override fun onResponse(
                call: Call<MutableList<repoData>>,
                response: Response<MutableList<repoData>>) {
                if(response.isSuccessful) {
                    userLiveData.value = response.body()
                }else{
                    print("ErrorMessage=${response.message()}")
                }
             }
        })

    }


    fun getUserDetail(repoName:String){
        val apiService = githubBuilder.invoke()
        apiService?.getUserDetail(repoName)?.enqueue(object : Callback<User> {
            override fun onFailure(
                call: Call<User>,
                t: Throwable) {
                println("Error=$t")
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>) {
                if (response.isSuccessful) {
                    userDetail.value = response.body()
                } else {
                    print("ErrorMessage=${response.message()}")

                }
            }
        })

    }


    fun getRepositoryList(repoName:String){
        val apiService = githubBuilder.invoke()
        apiService?.getRepositoryList(repoName)?.enqueue(object : Callback<MutableList<RepositoryList>> {
            override fun onFailure(
                call: Call<MutableList<RepositoryList>>,
                t: Throwable) {
                println("Error=$t")
            }

            override fun onResponse(
                call: Call<MutableList<RepositoryList>>,
                response: Response<MutableList<RepositoryList>>) {
                if (response.isSuccessful) {
                    repositoryList.value = response.body()
                } else {
                    print("ErrorMessage=${response.message()}")

                }
            }
        })

    }

}