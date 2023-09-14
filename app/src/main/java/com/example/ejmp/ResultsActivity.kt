package com.example.ejmp
import ExpenseAdapter
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultsActivity : AppCompatActivity() {

    private lateinit var expenseList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpList()

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchExpenses(query)
            }
        }
    }

    private fun setUpList() {
        expenseList = findViewById(R.id.list)
        val adapter = ExpenseAdapter()
        expenseList.adapter = adapter
        expenseList.layoutManager = LinearLayoutManager(this)

        adapter.submitList(ExpenseRepository.getAll())
    }

    private fun searchExpenses(query: String) {
        val searchResults = ExpenseRepository.search(query)
        (expenseList.adapter as ExpenseAdapter).submitList(searchResults)
    }
}
