package digital.heylab.androidbasesimple.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import digital.heylab.androidbasesimple.databinding.ActivityMainBinding
import digital.heylab.androidbasesimple.utils.resource.Resource
import digital.heylab.androidbasesimple.utils.resource.Status
import digital.heylab.androidbasesimple.utils.resource.Status.*
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
        viewModel.pokemon.observe(this) { pokemon ->
            when (pokemon.status) {
                SUCCESS -> {
                    Toast.makeText(this, "Pokemon: ${pokemon.data?.location}", Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                ERROR -> {
                    Toast.makeText(this, "Deu ruim", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun setupEvents() {
        binding.texto.setOnClickListener {
            viewModel.getPokemon("ditto")
        }
    }
}