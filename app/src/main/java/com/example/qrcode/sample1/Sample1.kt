package com.example.qrcode.sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qrcode.databinding.ActivitySample1Binding
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import java.lang.Exception

class Sample1 : AppCompatActivity() {

    private val binding by lazy {
        ActivitySample1Binding.inflate(layoutInflater)
    }

    private val barcodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                val originalIntent = result.originalIntent
                if (originalIntent == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                    Toast.makeText(
                        this,
                        "Cancelled due to missing camera permission",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btnScan.setOnClickListener {
            qrCode()
        }
        btnGenerate.setOnClickListener {
            generate()
        }
    }

    private fun generate() {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(
                "https://arashaltafi.ir",
                BarcodeFormat.QR_CODE,
                400,
                400
            )
            binding.imgQrCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun qrCode() {
        barcodeLauncher.launch(ScanOptions())
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
        options.setPrompt("Scan a barcode")
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }

}