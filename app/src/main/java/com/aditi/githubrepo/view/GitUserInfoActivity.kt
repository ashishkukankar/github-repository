package com.aditi.githubrepo.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditi.githubrepo.R.id
import com.aditi.githubrepo.R.layout
import com.aditi.githubrepo.viewModel.GitRepoViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class GitUserInfoActivity: AppCompatActivity() {

    lateinit var userName:TextView
    lateinit var email:TextView
    lateinit var location:TextView
    lateinit var joinDate:TextView
    lateinit var follower:TextView
    lateinit var following:TextView
    lateinit var userImage:ImageView
    lateinit var repositoryRecycleView:RecyclerView
    lateinit var viewModel:ViewModel
    lateinit var recycleAdapter:RepositoryAdapter
     var repoName:String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoName = intent.getStringExtra("repo_Name")
        setContentView(layout.activity_userdetail)
        viewModel = ViewModelProvider(this).get(GitRepoViewModel::class.java)

        inItView()

    }

    private fun inItView() {
        userName = findViewById(id.username)
        email = findViewById(id.email)
        location = findViewById(id.location)
        joinDate = findViewById(id.date)
        follower = findViewById(id.follower)
        following= findViewById(id.following)
        userImage= findViewById(id.user_image)
        repositoryRecycleView = findViewById(id.repository_details)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        repositoryRecycleView.layoutManager = layoutManager


        (viewModel as GitRepoViewModel).getUser(repoName).observe(this, Observer {
            userName.text  = it.name
            email.text     = it.email
            location.text  = it.location
            joinDate.text  = getDateFormated(it.created_at)
            follower.text  = "${it.followers} followers"
            following.text = "${it.following} following"
            Glide.with(this).load(it.avatar_url)
                .thumbnail(Glide.with(this).load(it.avatar_url).apply(RequestOptions().override(50,50)))
                .into(userImage)

        })

        (viewModel as GitRepoViewModel).getRepositoryList(repoName).observe(this, Observer {
            recycleAdapter = RepositoryAdapter(it)
            repositoryRecycleView.adapter = recycleAdapter

        })

    }


    fun getDateFormated(input:String):String{
        val inputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
        val date = LocalDate.parse(input, inputFormatter)
        val formattedDate = outputFormatter.format(date)

        return formattedDate
    }
}