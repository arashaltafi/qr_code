/**
 * To Generate Sample Qr Code ===> http://goqr.me/
 */

package com.example.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrcode.sample1.Sample1
import com.example.qrcode.sample2.Sample2
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btn1 : MaterialButton
    private lateinit var btn2 : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)

        btn1.setOnClickListener {
            startActivity(Intent(this , Sample1::class.java))
        }

        btn2.setOnClickListener {
            startActivity(Intent(this , Sample2::class.java))
        }
    }

}