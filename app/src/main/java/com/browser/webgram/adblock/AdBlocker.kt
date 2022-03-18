package com.browser.webgram.adblock

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

object AdBlocker {
    lateinit var blocklist: StringBuilder
    var load_normal_list = "0"

    fun loadBlockList(context: Context, int: Int) { //Blocklist loading
        var strLine: String? = ""
        blocklist = StringBuilder()
        val fis = context.resources.openRawResource(int) //Storage location
        val bufferedReader = BufferedReader(InputStreamReader(fis))
        try {
            while (bufferedReader.readLine().also { strLine = it } != null) {
                if (load_normal_list == "0") {
                    blocklist.append(strLine) //if ":::::" exists in blocklist | Line for Line
                    blocklist.append("\n")
                }
                if (load_normal_list == "1") {
                    blocklist.append(":::::$strLine") //if ":::::" not exists in blocklist | Line for Line
                    blocklist.append("\n")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
