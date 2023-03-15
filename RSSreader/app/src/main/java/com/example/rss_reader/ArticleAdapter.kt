package com.example.rss_reader

import android.annotation.SuppressLint
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) =
        holder.bind(articles[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val pubDate = itemView.findViewById<TextView>(R.id.pubDate)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        @SuppressLint("SetJavaScriptEnabled")
        fun bind(article: Article) {
            var pubDateString = article.pubDate

            try {
                val sourceDateString = article.pubDate
                val sourceSdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH)

                if (sourceDateString != null) {
                    val date = sourceSdf.parse(sourceDateString)
                    if (date != null) {
                        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                        pubDateString = sdf.format(date)
                    }
                }
            } catch (_: Exception) {
            }

            if (article.title != null) {
                title.text = article.title
            }

            if (article.image != null) {
                Picasso.get()
                    .load(article.image)
                    .placeholder(R.drawable.placeholder)
                    .into(image)
            } else {
                image.visibility = View.GONE
            }

            pubDate.text = pubDateString

            itemView.setOnClickListener {
                val articleView = WebView(itemView.context)

                articleView.settings.loadWithOverviewMode = true
                articleView.settings.javaScriptEnabled = true
                articleView.isHorizontalScrollBarEnabled = false
                articleView.webChromeClient = WebChromeClient()

                articleView.loadDataWithBaseURL(
                    null,
                    "<style>img{display: inline; height: auto; max-width: 100%;} " +
                            "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + article.content,
                    null,
                    "utf-8",
                    null
                )

                val alertDialog =
                    androidx.appcompat.app.AlertDialog.Builder(itemView.context).create()

                alertDialog.setTitle(article.title)
                alertDialog.setView(articleView)

                alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK")
                { dialog, _ -> dialog.dismiss() }

                alertDialog.show()

                (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod =
                    LinkMovementMethod.getInstance()
            }
        }
    }
}