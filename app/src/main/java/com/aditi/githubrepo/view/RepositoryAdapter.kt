package com.aditi.githubrepo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditi.githubrepo.R
import com.aditi.githubrepo.view.RepositoryAdapter.*
import com.aditi.githubrepo.viewModel.api.GithubRepoData.*

class RepositoryAdapter(var repositoryList: MutableList<RepositoryList>): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.repository_list_item, null)
        return RecyclerViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
    return repositoryList.count()
     }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.repoName.setText(this.repositoryList!![position].name)
        holder.fork.text = "${repositoryList!![position]!!.forks} forks"
        holder.star.text = "${repositoryList!![position].stargazers_count} stars"
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var repoName: TextView = itemView.findViewById(R.id.repo_name)
        var fork: TextView = itemView.findViewById(R.id.fork)
        var star: TextView = itemView.findViewById(R.id.star)
        override fun onClick(v: View?) {

        }

    }
}