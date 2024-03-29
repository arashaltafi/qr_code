package com.example.qrcode.sample2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import android.content.pm.PackageManager
import android.os.Build
import com.example.qrcode.databinding.ActivitySample2Binding

class Sample2 : AppCompatActivity() {

    private val binding by lazy {
        ActivitySample2Binding.inflate(layoutInflater)
    }

    private lateinit var codeScanner: CodeScanner
    private var mPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkPermission()
        init()
    }

    private fun init() = binding.apply {
        codeScanner = CodeScanner(this@Sample2, scannerView)

        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this@Sample2, it.text, Toast.LENGTH_LONG).show()
            }
            finish()
        }

        codeScanner.setErrorCallback {
            runOnUiThread {
                Toast.makeText(this@Sample2, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        scannerView.isFlashButtonVisible = false
        scannerView.isAutoFocusButtonVisible = false
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = false
                requestPermissions(arrayOf(Manifest.permission.CAMERA), RC_PERMISSION)
            } else {
                mPermissionGranted = true
            }
        } else {
            mPermissionGranted = true
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    companion object {
        private const val RC_PERMISSION = 10
    }

}