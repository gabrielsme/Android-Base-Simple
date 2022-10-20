package digital.heylab.androidbasesimple.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import digital.heylab.androidbasesimple.databinding.ActivityMainBinding
import digital.heylab.androidbasesimple.utils.resource.Status.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupEvents()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.movie.collect { movie ->
                var text = ""
                when (movie.status) {
                    SUCCESS -> {
                        text = "Movie: ${movie.data?.title}"
                    }
                    LOADING -> {
                        text = "Loading"
                    }
                    ERROR -> {
                        text = "Deu ruim"
                    }
                }

                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
                binding.texto.text = text
            }
        }
    }

    private fun setupEvents() {
        binding.texto.setOnClickListener {
            viewModel.getMovieById("tt1285016")
        }
    }
}