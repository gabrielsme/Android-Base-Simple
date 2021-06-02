package digital.heylab.androidbasesimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import digital.heylab.androidbasesimple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.texto.setOnClickListener {
            Toast.makeText(this, "Eita porra", Toast.LENGTH_SHORT).show()
        }
    }
}