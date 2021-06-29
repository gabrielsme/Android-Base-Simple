package digital.heylab.androidbasesimple.data.repository

import digital.heylab.androidbasesimple.data.repository.mapper.PokemonMapper
import digital.heylab.androidbasesimple.data.repository.model.Pokemon
import digital.heylab.androidbasesimple.data.source.remote.RemoteService

class RemoteRepository constructor(
    private val remoteService: RemoteService
) {

    suspend fun getPokemon(name: String): Pokemon {
        val pokemon = remoteService.getPokemon(name)
        return PokemonMapper().mapper(pokemon)
    }

}
