package io.github.a2kaido.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class FlowActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    lateinit var parent: Job

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        title = "Flow"

        parent = launch {
            try {
                flow {
                    for (i in 0..10) {
                        emit(i)
                    }
                }.collect {
                    delay(100)
                    parent_coroutine.text = it.toString()
                }
            } catch (e: Exception) {
                parent_coroutine.text = e.message
            }
        }

        stop_parent.setOnClickListener {
            parent.cancel()
        }
    }
}
