package io.github.a2kaido.coroutines

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.a2kaido.coroutines.adapter.MainRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainRecyclerViewAdapter {
            when (it) {
                1 -> {
                    startActivity(Intent(this, ZipActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(this, ChannelActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(this, FlowActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, CoroutinesActivity::class.java))
                }
            }
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        adapter.submitList(listOf(
            "Chapter 1",
            "Zip",
            "Channel",
            "Flow"
        ))
    }
}
