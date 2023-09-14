package com.example.ejmp

import ExpenseAdapter
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var expenseList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
            sm.getSearchableInfo(
                ComponentName(this, MainActivity::class.java)
            )
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val searchResults = ExpenseRepository.search(it)
                    (expenseList.adapter as ExpenseAdapter).submitList(searchResults)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        return true
    }

    private fun setUpList() {
        expenseList = findViewById(R.id.list)
        val adapter = ExpenseAdapter()
        expenseList.adapter = adapter
        expenseList.layoutManager = LinearLayoutManager(this)

        adapter.submitList(ExpenseRepository.getAll())
    }
}




















































