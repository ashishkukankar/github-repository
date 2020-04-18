package com.aditi.githubrepo.viewModel.api

class GithubRepoData {



    data class repoData( var login: String,
                         var id: Int,
                         var node_id: String,
                         var avatar_url: String,
                         var url: String,
                         var html_url: String,
                         var followers_url: String,
                         var following_url: String,
                         var gists_url: String,
                         var starred_url: String,
                          var organizations_url: String,
                         var repos_url: String,
                         var events_url: String,
                         var received_events_url: String,
                         var type: String,
                         var site_admin: String
    )


    data class User(var login: String,
                    var id: Int,
                    var node_id: String,
                    var avatar_url: String,
                    var name: String,
                    var company: String,
                    var location: String,
                    var email: String,
                    var public_repos: String,
                    var followers: String,
                    var following: String,
                    var created_at: String,
                    var updated_at: String,
                    var repos_url: String
                    )


    data class RepositoryList(var name:String,
                              var stargazers_count:String,
                              var forks:String)
}



