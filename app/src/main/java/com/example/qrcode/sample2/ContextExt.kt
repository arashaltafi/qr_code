package com.example.qrcode.sample2

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object ContextExt {

    fun Context.hasPermissions(vararg permission: String): Boolean {
        var result = true

        permission.forEach {
            val a = ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
            result = result && a
        }

        return result
    }

}