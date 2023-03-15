package com.example.rss_reader

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rss_reader.util.AlertDialogHelper
import com.prof.rssparser.Parser
import org.xml.sax.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticleAdapter
    private lateinit var parser: Parser

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val swipeLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_layout)

        setSupportActionBar(toolbar)

        parser = Parser.Builder()
            .context(this)
            .build()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
/*
Когда данные изменяются, когда владелец не активен, они не будут получать никаких обновлений.
Если он снова станет активным, он автоматически получит последние доступные данные.
 */
        viewModel.rssChannel.observe(this) { channel ->
            if (channel != null) {
                if (channel.title != null) {
                    title = channel.title
                } else {
                    title = "Empty XML Feed"
                }
                adapter = ArticleAdapter(channel.articles)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                swipeLayout.isRefreshing = false
            }

        }

        swipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipeLayout.canChildScrollUp()

        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = false
        }

        if (!isOnline()) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.alert_message)
                .setTitle(R.string.alert_title)
                .setCancelable(false)
                .setPositiveButton(
                    R.string.alert_positive
                ) { _, _ -> finish() }

            val alert = builder.create()
            alert.show()
        } else if (isOnline()) {
            viewModel.fetchFeed(parser)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_raw_parser) {
            AlertDialogHelper(
                title = R.string.alert_raw_parser_title,
                positiveButton = R.string.alert_raw_parser_positive,
                negativeButton = R.string.alert_raw_parser_negative
            ).buildInputDialog(this) { url ->
                viewModel.fetchForUrlAndParseRawData(url)
            }.show()
        }

        return super.onOptionsItemSelected(item)
    }

    @Suppress("DEPRECATION")
    fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}


