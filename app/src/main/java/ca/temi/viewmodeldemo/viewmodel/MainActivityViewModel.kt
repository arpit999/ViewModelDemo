package ca.temi.viewmodeldemo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import ca.temi.viewmodeldemo.model.Quote
import com.google.gson.Gson
import java.nio.charset.Charset
import java.util.*

class MainActivityViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAsset()
    }

    private fun loadQuoteFromAsset(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)

        val gson = Gson()
            .fromJson(json, Array<Quote>::class.java)

        return gson
    }

    fun getQuote()= quoteList[index]
    fun nextQuote()= quoteList[++index % quoteList.size]
    fun previousQuote()= quoteList[(--index + quoteList.size) % quoteList.size]

}