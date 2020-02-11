package com.hiepsummer.roomdbdemo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.hiepsummer.roomdbdemo.R
import kotlinx.android.synthetic.main.activity_add_data.*

class AddDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        actionAdd()
    }

    fun actionAdd() {
        button_save.setOnClickListener(View.OnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        })
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
