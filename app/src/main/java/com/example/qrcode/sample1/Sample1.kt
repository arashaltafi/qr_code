package com.example.qrcode.sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.qrcode.R
import com.google.android.material.button.MaterialButton
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import java.lang.Exception

class Sample1 : AppCompatActivity() {

    //region widgets
    private lateinit var btnScan : MaterialButton
    private lateinit var btnGenerate : MaterialButton
    private lateinit var imgQrCode : ImageView
    //endregion

    //region barcode
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
            else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Toast.makeText(this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        // region Find Views
        imgQrCode = findViewById(R.id.img_qr_code)
        btnScan = findViewById(R.id.btn_scan)
        btnGenerate = findViewById(R.id.btn_generate)
        //endregion

        // region On Click Listeners
        btnScan.setOnClickListener {
            qrCode()
        }
        btnGenerate.setOnClickListener {
            generate()
        }
        //endregion

    }

    private fun generate() {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap("https://arashaltafi.ir", BarcodeFormat.QR_CODE, 400, 400)
            imgQrCode.setImageBitmap(bitmap)
        }
        catch (e: Exception) {
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