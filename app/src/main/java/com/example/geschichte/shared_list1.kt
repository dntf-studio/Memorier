package com.example.geschichte

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.io.BufferedReader
import java.io.File
import java.lang.StringBuilder

class shared_list1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_list1)

        val  dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        val editor = dataStore.edit()
        var rndm_c_i = intent.getIntExtra("key",0)
        var c_i = intent.getIntExtra("key?c",0)
        var rndm_c = rndm_c_i -1
        var c = c_i -1

        val text :TextView = findViewById(R.id.txtBoxList)
        val numBox :EditText = findViewById(R.id.editTextNumber)
        val btnSend :Button = findViewById(R.id.btnRemove)

        val fileName = "/data/data/com.example.geschichte/files/STRING_DATA.txt"
        val file = File("/data/data/com.example.geschichte/files/STRING_DATA.txt")
        val br = file.bufferedReader()
        var STRING: MutableList<String> = br.readText().split(',').toMutableList()

        fun makeList(i:Int,length:Int,strBuilder:StringBuilder){
            for(ii in 0..i){
                var ln:String? = null
                var il:List<String>
                var i2 = StringBuilder()
                var count = ii+1
                if(STRING[ii].lines().count() > 0 ){
                    if(STRING[ii].lines()[0].length > length){
                        il = STRING[ii].split(STRING[ii].substring(length))
                        strBuilder.append("Question"+count+":"+il[0]+"..."+"\n")
                    }
                    else{
                        ln = STRING[ii].lines()[0]
                        strBuilder.append("Question"+count+":"+ln.toString()+"..."+"\n")
                    }
                }
                else if(STRING[ii].lines()[0].length > length){
                    for (iww in 0..length){
                        i2.append(STRING[ii].substring(iww))
                    }
                    strBuilder.append("Question"+count+":"+i2.lines()[0]+"..."+"\n")
                }
                else{strBuilder.append("Question"+count+":"+STRING[ii]+"..."+"\n")}
            }
        }
        val QSSTC = StringBuilder()
        if(br.readText() != null){
            makeList(c,15,QSSTC)
        }
        text.text = QSSTC

        fun initSTRING_DATA(DATA:String){
            val fileName_out = "STRING_DATA.txt"
            applicationContext.openFileOutput(fileName_out, MODE_PRIVATE).use {
                it.write(DATA.toByteArray())
            }
        }

        fun appendSTRING_DATA(DATA:String){
            val fileName_out = "STRING_DATA.txt"
            applicationContext.openFileOutput(fileName_out, MODE_APPEND).use {
                it.write(DATA.toByteArray())
            }
        }

        val textviewer :TextView = findViewById(R.id.textView)
        var remove_num:Int
        btnSend.setOnClickListener {
            if(numBox.text.toString() != ""){
                var counter = STRING.count()
                textviewer.text = counter.toString()
                var num = numBox.text.toString().toInt()
                STRING.removeAt(num-1)
                initSTRING_DATA("")
                STRING.forEach {a_STRING ->
                    appendSTRING_DATA(a_STRING+',')
                }
            }
        }
    }
}