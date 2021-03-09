package com.example.geschichte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.File
import kotlin.random.Random

class from1929 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_from1929)
        val question_num = intent.getIntExtra("key",0)
        val question_txt = intent.getStringExtra("key2")
        val keyqlistmutableadd = intent.getStringExtra("")
        val keyli = intent.getStringExtra("keyli")
        /*val TC1 = intent.getStringExtra("key?c1")
        val TC2 = intent.getStringExtra("key?c2")
        val TC3 = intent.getStringExtra("key?c3")*/

        val TC1_t :TextView= findViewById(R.id.TC1)
        val TC2_t:TextView = findViewById(R.id.TC2)
        val TC3_t :TextView = findViewById(R.id.TC3)
        val txtQ : TextView = findViewById(R.id.question)
        val btnBack : Button = findViewById(R.id.btnBack)
        val question_box : TextView = findViewById(R.id.BOX4Q)
        val text__ = getString(R.string.QUESTION_SETTEXT,question_num.toString())
        txtQ.text = text__

        val lQQ = mutableListOf<String>()
        btnBack.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val file = File("/data/data/com.example.geschichte/files/STRING_DATA.txt")
        val br = file.bufferedReader()
        var STRING:List<String> =  br.readText().split(',')
        question_box.text = STRING[question_num-1]
    }
}