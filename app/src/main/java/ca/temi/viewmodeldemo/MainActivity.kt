package ca.temi.viewmodeldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ca.temi.viewmodeldemo.factory.MainActivityViewModelFactory
import ca.temi.viewmodeldemo.model.Quote
import ca.temi.viewmodeldemo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel


    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel =
            ViewModelProvider(this, MainActivityViewModelFactory(application)).get(
                MainActivityViewModel::class.java
            )
        setQuote(mainActivityViewModel.getQuote())

    }

    fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, mainActivityViewModel.getQuote().text)
        startActivity(intent)
    }

    fun onPrevious(view: View) {
        setQuote(mainActivityViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainActivityViewModel.nextQuote())
    }
}