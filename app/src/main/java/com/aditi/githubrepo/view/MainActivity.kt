package com.aditi.githubrepo.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditi.githubrepo.R
import com.aditi.githubrepo.listner.RecyclerClickListerner
import com.aditi.githubrepo.viewModel.GitRepoViewModel

class MainActivity : AppCompatActivity(),
    View.OnClickListener,RecyclerClickListerner{



    lateinit var recycleView:RecyclerView
    lateinit var recyclerAdapter: GitRecyclerAdapter
    var searchView: SearchView? =null
    lateinit var filterString:String
    lateinit var  viewModel:ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inItView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_resource,menu)
        val menuItem = menu?.findItem(R.id.search)


        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if(menuItem !==null) {
            searchView = menuItem!!.actionView as SearchView
            searchView?.maxWidth = Int.MAX_VALUE
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
            val closebutton =
                searchView?.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
            closebutton?.setOnClickListener(this)
        }
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.length >= 3) {
                    filterString =newText
                    recyclerAdapter.filter.filter(newText)
                }

                return  false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }



    private fun inItView() {
        viewModel = ViewModelProvider(this).get(GitRepoViewModel::class.java)
        recycleView = findViewById(R.id.recycler_view)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView.layoutManager = layoutManager

        (viewModel as GitRepoViewModel).getAllUser().observe(this, Observer {
            it.let {
                recyclerAdapter = GitRecyclerAdapter(this,it,this)
                recycleView.adapter = recyclerAdapter
            }

        })

    }

    override fun onClick(v: View?) {
        recyclerAdapter.reset( (viewModel as GitRepoViewModel).getAllUser().value!!)
        filterString = ""
        searchView?.setQuery("", false)
        searchView?.onActionViewCollapsed()
        searchView?.isIconified = true


    }

    override fun onItemClickListern(item: Int) {
        val repoName =   (viewModel as GitRepoViewModel).getAllUser().value!!.get(item).login
        val intent = Intent(this,GitUserInfoActivity::class.java)
        intent.putExtra("repo_Name",repoName)
        startActivity(intent)
     }

}
