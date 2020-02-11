package com.hiepsummer.roomdbdemo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiepsummer.roomdbdemo.R
import com.hiepsummer.roomdbdemo.model.Word
import com.hiepsummer.roomdbdemo.adapter.WordListAdapter
import com.hiepsummer.roomdbdemo.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var wordViewModel: WordViewModel
    private val AddDataActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter(this)
        recyclerViewdata.adapter = adapter
        recyclerViewdata.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWorlds.observe(this, Observer { words ->
            words.let { adapter.setWorlds(it) }
        })
        action()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddDataActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddDataActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(applicationContext,
                R.string.empty_not_save, Toast.LENGTH_LONG).show()
        }
    }

    fun action() {
        floatingActionButton.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(this, AddDataActivity::class.java)
            startActivityForResult(intent, AddDataActivityRequestCode)
        })
    }

}
