package digital.heylab.androidbasesimple.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import digital.heylab.androidbasesimple.databinding.ActivityMainBinding
import digital.heylab.androidbasesimple.utils.resource.Status.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupEvents()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.pokemon.collect { pokemon ->
                var text = ""
                when (pokemon.status) {
                    SUCCESS -> {
                        text = "Pokemon: ${pokemon.data?.location}"
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
            viewModel.getPokemon("ditto")
        }
    }
}