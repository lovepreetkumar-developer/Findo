package com.techfathers.findo.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.telephony.TelephonyManager
import com.techeytech.rich_dad_poor_dad.data.preferences.PreferenceProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class PhoneStateReceiver : BroadcastReceiver(), KodeinAware {

    override lateinit var kodein: Kodein
    private lateinit var audioManager: AudioManager
    private val preferences: PreferenceProvider by instance()

    override fun onReceive(context: Context?, intent: Intent) {
        try {

            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            //context?.toast(incomingNumber.toString())
            if (incomingNumber != null) {
                kodein = (context?.applicationContext as KodeinAware).kodein
                audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

                if (audioManager.ringerMode != AudioManager.RINGER_MODE_NORMAL) {
                    val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
                    if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                        disableSilentAndUpVolume(incomingNumber)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun disableSilentAndUpVolume(
        incomingNumber: String
    ) {
        if (incomingNumber.contains(
                checkValueExist(preferences.getFirstNumber()),
                ignoreCase = true
            )
        ) {
            if (preferences.getFirstNumberCounter() >= 1) {
                //context.toast("do action executed")
                doAction()
            } else {
                //context.toast("incremented")
                preferences.setFirstNumberCounter(preferences.getFirstNumberCounter() + 1)
            }
        } else {
            if (incomingNumber.contains(
                    checkValueExist(preferences.getSecondNumber()),
                    ignoreCase = true
                )
            ) {
                if (preferences.getSecondNumberCounter() >= 1) {
                    doAction()
                } else {
                    preferences.setSecondNumberCounter(preferences.getSecondNumberCounter() + 1)
                }
            }
        }
    }

    private fun checkValueExist(firstNumber: String): String {
        if (firstNumber == "") return "null"
        return firstNumber
    }

    private fun doAction() {
        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);
        preferences.setFirstNumberCounter(0)
        preferences.setSecondNumberCounter(0)
    }
}