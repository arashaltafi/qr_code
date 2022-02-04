package com.example.qrcode.sample2

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import com.example.qrcode.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_2.*

class Fragment2 : Fragment() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listener()
    }

    private fun init() {
        codeScanner = CodeScanner(requireActivity(), scanner_view)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        scanner_view.isFlashButtonVisible = false
        scanner_view.isAutoFocusButtonVisible = false
    }

    private fun listener() {

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }

        val activity = requireActivity()

        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
            }
//            findNavController().navigateUp()
        }

        codeScanner.setErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.message , Toast.LENGTH_SHORT).show()
//                findNavController().navigateUp()
            }
        }

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
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


}