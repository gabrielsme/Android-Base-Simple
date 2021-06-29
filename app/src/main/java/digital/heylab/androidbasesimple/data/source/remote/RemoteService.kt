package digital.heylab.androidbasesimple.data.source.remote

import digital.heylab.androidbasesimple.data.source.remote.model.PokemonModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonModel

}
