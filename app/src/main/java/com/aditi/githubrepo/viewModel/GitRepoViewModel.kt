package com.aditi.githubrepo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditi.githubrepo.viewModel.api.GithubRepoData
import com.aditi.githubrepo.viewModel.api.GithubRepoData.*

class GitRepoViewModel:ViewModel() {
    val repository = GitRepoRepository()
    init {
        repository.getAllUser()
    }


    fun getAllUser():LiveData<MutableList<repoData>>{
    return  repository.getGitRepo()!!
    }

    fun getUser(user:String):LiveData<User>{
        repository.getUserDetail(user)
        return  repository.getUser()!!
    }

    fun getRepositoryList(user:String):LiveData<MutableList<RepositoryList>>{
        repository.getRepositoryList(user)
        return  repository.getRepositorList()!!
    }
}