package io.github.a2kaido.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext

class ChannelActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    lateinit var parent: Job

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        title = "Channel"

        val channel = Channel<Int>()

        parent = launch {
            for (count in 0..10) {
                channel.send(count)
            }

            channel.close()
        }

        launch {
            for (a in channel) {
                delay(1000)
                parent_coroutine.text = a.toString()
            }

            parent_coroutine.text = "DONE!"
        }

        stop_parent.setOnClickListener {
            parent.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }
}
