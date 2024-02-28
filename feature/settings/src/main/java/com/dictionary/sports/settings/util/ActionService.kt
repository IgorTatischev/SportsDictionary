package com.dictionary.sports.settings.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.dictionary.sports.settings.R

class ActionService(private val context: Context) {

    fun openEmail() {
        val emailAddress = arrayOf(email)
        val intent = Intent(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_EMAIL, emailAddress)
            .setType("message/rfc822")

        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.mail_title)
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun openMarket() {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${context.packageName}")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    companion object {
        const val email = "support@gmail.com"
    }
}