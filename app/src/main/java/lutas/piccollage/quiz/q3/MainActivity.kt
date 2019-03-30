package lutas.piccollage.quiz.q3

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import lutas.piccollage.quiz.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity: AppCompatActivity() {

    private lateinit var viewModel: GameOfLifeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProvider(this,
            GameOfLifeViewModel.Factory()).get(GameOfLifeViewModel::class.java)
        initView()
        observeData()
    }

    private fun initView() {
        start_button.setOnClickListener {
            if (viewModel.isRunning.value == true) {
                viewModel.stop()
            } else {
                viewModel.start()
            }
        }

        field.apply {
            onLayoutListener = { columns, rows ->
                viewModel.setWorld(columns, rows)
            }
            onDownListener = { column, row ->
                viewModel.addLife(column, row)
            }
        }
    }

    private fun observeData() {
        observe(viewModel.world) {
            field.world = it
        }
        observe(viewModel.isRunning) {
            start_button.text = if (it == true) "Stop" else "Start"
        }
    }

    override fun onDestroy() {
        viewModel.stop()
        super.onDestroy()
    }
}