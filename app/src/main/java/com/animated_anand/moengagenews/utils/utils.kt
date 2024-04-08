package com.animated_anand.moengagenews.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.animated_anand.moengagenews.databinding.ProgressDialogBinding

object Utils {
    var progressDialog: AlertDialog? = null

    // Show a toast message
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Show a progress dialog
    fun showProgressDialog(context: Context, message: String) {
        val binding = ProgressDialogBinding.inflate(LayoutInflater.from(context))
        binding.tvProgressDialog.text = message
        progressDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()
        progressDialog!!.show()
    }

    // Hide the progress dialog
    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }
}
