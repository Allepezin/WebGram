package com.browser.webgram.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Ip {
    @SerializedName("query")
    @Expose
    var ip: String? = null
}
