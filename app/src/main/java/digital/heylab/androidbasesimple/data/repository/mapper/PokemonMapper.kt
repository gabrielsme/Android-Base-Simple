package digital.heylab.androidbasesimple.data.repository.mapper

import digital.heylab.androidbasesimple.data.repository.model.Pokemon
import digital.heylab.androidbasesimple.data.source.remote.model.PokemonModel

class PokemonMapper {
    fun mapper(model: PokemonModel): Pokemon {
        return Pokemon(
            name = model.name
        )
    }
}