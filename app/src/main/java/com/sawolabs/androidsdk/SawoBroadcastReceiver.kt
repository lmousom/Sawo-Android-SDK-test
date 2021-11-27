package com.sawolabs.androidsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SawoBroadcastReceiver : BroadcastReceiver() {

    private lateinit var cryptographyManager: CryptographyManager

    override fun onReceive(context: Context?, intent: Intent?) {
        cryptographyManager = CryptographyManager()

        val keyExistInStorage = context?.let { c->
            cryptographyManager.isDataExistInSharedPrefs(
                c,
                SHARED_PREF_FILENAME,
                Context.MODE_PRIVATE,
                SHARED_PREF_ENC_PAIR_KEY
            )
        }

        if (keyExistInStorage == true){
            val i = Intent()
            val dataObj = context.getSharedPreferences(
                SHARED_PREF_FILENAME,
                0
            ).getString(
                SHARED_PREF_ENC_PAIR_KEY,
                null
            )

            i.putExtra("key", dataObj)
            i.action = "com.sawolabs.crypto"
            

        }
    }
}