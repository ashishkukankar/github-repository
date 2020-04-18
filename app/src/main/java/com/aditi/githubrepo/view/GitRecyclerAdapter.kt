package com.aditi.githubrepo.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aditi.githubrepo.R
import com.aditi.githubrepo.listner.RecyclerClickListerner
import com.aditi.githubrepo.view.GitRecyclerAdapter.RecyclerViewHolder
import com.aditi.githubrepo.viewModel.api.GithubRepoData.repoData
import com.bumptech.glide.Glide

class GitRecyclerAdapter(var context:Context,var gitUserList:MutableList<repoData>?,var listener:RecyclerClickListerner): RecyclerView.Adapter<RecyclerViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.git_user_items, null)
        return RecyclerViewHolder(layoutView,listener)
    }

    override fun getItemCount(): Int {
        return gitUserList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.userName.setText(this.gitUserList!![position].login)
        holder.userRepoId.setText(gitUserList!![position]!!.id.toString())
        Glide.with(context).load(gitUserList!![position].avatar_url).into(holder.imageView)
    }

    fun reset(list: MutableList<repoData>) {
        gitUserList = list
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val searchedList = results!!.values as MutableList<repoData>
                if (searchedList.size > 0)
                    gitUserList = searchedList

                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var temp: MutableList<repoData> = arrayListOf()
                var results = FilterResults()
                gitUserList.let {
                    for (content in it!!)
                        if ((content.login.toLowerCase()).contains(constraint.toString().toLowerCase())) {
                            temp.add(content)
                        }
                }
                results.values = temp
                results.count = temp.size
                return results
            }


        }
    }

    class RecyclerViewHolder(itemView: View,listener: RecyclerClickListerner) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val mListerner = listener
        var imageView: ImageView = itemView.findViewById(R.id.user_photo)
        var userName: TextView = itemView.findViewById(R.id.user_name)
        var userRepoId: TextView = itemView.findViewById(R.id.user_repoid)
        var layout:CardView = itemView.findViewById(R.id.recycler_layout)
        init {
            layout.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            mListerner.onItemClickListern(adapterPosition)
        }

    }
}