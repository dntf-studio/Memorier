package com.example.geschichte

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        var hellotext:TextView = findViewById((R.id.hellotext))
        var btn1929:Button = findViewById(R.id.btn1929)
        var txtnowQ:TextView = findViewById(R.id.questionCount)
        var btnIncre:Button = findViewById(R.id.increment)
        btnIncre.text = "インクリ"
        var btnDecre:Button = findViewById(R.id.increment2)
        btnDecre.text = "デクリ"
        var checkBox1 : CheckBox = findViewById(R.id.checkBox1)
        var checkBox2 : CheckBox = findViewById(R.id.checkBox2)
        var checkBox3 : CheckBox = findViewById(R.id.checkBox3)
        var btnAdd :Button = findViewById(R.id.btnAdd)
        val init :Button = findViewById(R.id.INIT)

        val  dataStore:SharedPreferences = getSharedPreferences("DataStore",Context.MODE_PRIVATE)
        val editor = dataStore.edit()

        var a = mutableSetOf<String>()

        val listQuestion = mutableListOf<String>()
        val listChoice1 = mutableListOf<String>()
        val listChoice2 = mutableListOf<String>()
        val listChoice3 = mutableListOf<String>()

        var c :Int= 0
        val c_i :Int = dataStore.getInt("DataCount",0)
        c = c_i
        val yeQQ = "今の問題数:"
        txtnowQ.text = yeQQ+c_i

        val textLIST :TextView= findViewById(R.id.aList)
        for (i in listQuestion){
            textLIST.text = "\n"+i.toString()
        }


        var CurrenlyChecked:Int? = 0
        checkBox1.setOnClickListener{
            if(checkBox2.isChecked)
                checkBox2.isChecked  =false
            if(checkBox3.isChecked)
                checkBox3.isChecked =false
            CurrenlyChecked = 1
        }

        checkBox2.setOnClickListener{
            if(checkBox1.isChecked)
                checkBox1.isChecked  =false
            if(checkBox3.isChecked)
                checkBox3.isChecked =false
            CurrenlyChecked = 2
        }

        checkBox3.setOnClickListener{
            if(checkBox1.isChecked)
                checkBox1.isChecked  =false
            if(checkBox2.isChecked)
                checkBox2.isChecked =false
            CurrenlyChecked = 3
        }

        var txtQuestion :String
        var txtQuestion2 :String
        var txtQuestion3 :String
        var txtQuestion4 :String

        val txtQuestionObj : EditText = findViewById(R.id.txtBoxQ)

        var txtChoice1 :String
        val txtChoice1Obj :EditText = findViewById(R.id.txtBox)
        var txtChoice2 :String
        val txtChoice2Obj :EditText = findViewById(R.id.txtBox2)
        var txtChoice3:String
        val txtChoice3Obj : EditText = findViewById(R.id.txtBox3)

        var checkAnswer :Int? = null
        var checkAdded = 0

        var key_str = "key:"+(c-1)
        var intent2 = Intent(this,shared_list1::class.java)

        fun change(cc:String){
            txtnowQ.text = yeQQ + cc
        }

        btnAdd.setOnClickListener {
            if (txtQuestionObj.text.toString() == "") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("問題文が記入されていません。")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice1Obj.text.toString() == "1:") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の1番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice2Obj.text.toString() == "2:") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の2番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice3Obj.text.toString() == "3:") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の3番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice1Obj.text.toString() == "") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の1番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice2Obj.text.toString() == "") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の2番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            } else if (txtChoice3Obj.text.toString() == "") {
                AlertDialog.Builder(this)
                        .setTitle("例外判定")
                        .setMessage("選択肢の3番が記入されていません")
                        .setPositiveButton("OK") { dialog, which -> }
                        .show()
            }

            if(txtQuestionObj.text.toString() != "" && txtChoice1Obj.text.toString()!= ""  &&txtChoice2Obj.text.toString() !=""&& txtChoice3Obj.text.toString()!= ""){
                if(txtChoice1Obj.text.toString() !="1:"&&txtChoice2Obj.text.toString()!="2:"&&txtChoice3Obj.text.toString()!="3:") {
                    checkAdded = 0
                    if(checkBox1.isChecked || checkBox2.isChecked || checkBox3.isChecked){
                        if(checkBox1.isChecked){
                            checkAdded = 1
                        }else if(checkBox2.isChecked){
                            checkAdded = 2
                        }else if (checkBox3.isChecked){
                            checkAdded = 3
                        }
                    }else if(checkAdded==0){
                        AlertDialog.Builder(this)
                                .setTitle("例外判定")
                                .setMessage("答えとなる選択肢の番号が選択されていません")
                                .setPositiveButton("OK") { dialog, which -> }
                                .show()
                    }

                    if(checkAdded>0){
                        //val aaaaa:Int = txtQuestionObj.text.lines().count()
                        //hellotext.text = aaaaa.toString()
                        txtQuestion=txtQuestionObj.text.toString()
                        txtChoice1=txtChoice1Obj.text.toString()
                        txtChoice2=txtChoice2Obj.text.toString()
                        txtChoice3=txtChoice3Obj.text.toString()
                        AlertDialog.Builder(this)
                                .setTitle("追加されました")
                                .setMessage("[問題内容]"+txtQuestion+"\n"+"選択肢1:「"+txtChoice1+"」\n"+"選択肢2:「"+txtChoice2+"」\n"+"選択肢3:「"+txtChoice3+"」\n"+"答え:"+checkAdded+"番")
                                .setPositiveButton("OK") { dialog, which -> }
                                .show()
                        c++
                        listQuestion.add(txtQuestion)
                        listChoice1.add(txtChoice1)
                        listChoice2.add(txtChoice2)
                        listChoice3.add(txtChoice3)

                        var keyq = "keyq"+c
                        intent2.putExtra(keyq,txtQuestion)
                        //editor.putString(key_str,txtQuestion)
                        textLIST.text = listQuestion.toString()

                        val TXTQQ_DATA = txtQuestion+","

                        val fileName = "STRING_DATA.txt"
                        applicationContext.openFileOutput(fileName, MODE_APPEND).use {
                            it.write(TXTQQ_DATA.toByteArray())
                        }

                    }
                }
            }

            change(c.toString())
            editor.putInt("DataCount",c)
            editor.apply()
        }

        btnIncre.setOnClickListener {
            c++
            change(c.toString())
            editor.putInt("DataCount",c)
            editor.apply()
        }

        btnDecre.setOnClickListener {
            if(c>0){
                c--
            }
            change(c.toString())
            editor.putInt("DataCount",c)
            editor.apply()
        }

        btn1929.setOnClickListener {
            var intent = Intent(this,from1929::class.java)

            val question_s = (1..c).random()

            val Lnumber :Int = c-1
            //intent.putExtra("key2",listQuestion.elementAt(Lnumber))
            /*intent.putExtra("key?c1",listChoice1[Lnumber])
            intent.putExtra("key?c2",listChoice2[Lnumber])
            intent.putExtra("key?c3",listChoice3[Lnumber])*/
            intent.putExtra("key",question_s)
            kotlin.runCatching {
                startActivity(intent)
            }
        }

        val btnActivity3_sharedPre:Button =  findViewById(R.id.button_list_string_shaedPre)

        btnActivity3_sharedPre.setOnClickListener {
            var intent2 = Intent(this,shared_list1::class.java)

            val question_s = (1..c).random()
            intent2.putExtra("key",question_s)
            intent2.putExtra("key?c",c)
            //intent.putExtra("keyli",listQuestion.elementAt(question_s))

            kotlin.runCatching {
                startActivity(intent2)
            }
        }

        fun initSTRING_DATA(DATA:String){
            val fileName_out = "STRING_DATA.txt"
            applicationContext.openFileOutput(fileName_out, MODE_PRIVATE).use {
                it.write(DATA.toByteArray())
            }
        }

        init.setOnClickListener {
            initSTRING_DATA("")
        }
    }


}



