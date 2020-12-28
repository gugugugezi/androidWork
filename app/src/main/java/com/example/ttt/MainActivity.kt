package com.example.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            button1.setOnClickListener {
                val intent = Intent(this,TraceActivity::class.java)
                intent.putExtra("id",num.text.toString())
                intent.putExtra("phone_num",phonenum.text.toString())
                startActivity(intent)
            }
        }
        }



