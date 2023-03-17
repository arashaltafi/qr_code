package com.example.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrcode.databinding.ActivityMainBinding
import com.example.qrcode.sample1.Sample1
import com.example.qrcode.sample2.Sample2

// to create qr code for test in web=> https://goqr.me/
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btn1.setOnClickListener {
            startActivity(Intent(this@MainActivity, Sample1::class.java))
        }

        btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity, Sample2::class.java))
        }
    }

}