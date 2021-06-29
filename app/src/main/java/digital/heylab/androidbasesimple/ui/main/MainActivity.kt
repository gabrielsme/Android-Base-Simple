package digital.heylab.androidbasesimple.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import digital.heylab.androidbasesimple.databinding.ActivityMainBinding
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
            Toast.makeText(this, "Pokemon: ${pokemon.data?.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEvents() {
        binding.texto.setOnClickListener {
            Toast.makeText(this, "Eita porra", Toast.LENGTH_SHORT).show()
            viewModel.getPokemon("ditto")
        }
    }
}