package digital.heylab.androidbasesimple.data.repository

import digital.heylab.androidbasesimple.data.repository.mapper.PokemonMapper
import digital.heylab.androidbasesimple.data.repository.model.Pokemon
import digital.heylab.androidbasesimple.data.source.remote.RemoteService
import digital.heylab.androidbasesimple.network.ResponseHandler
import digital.heylab.androidbasesimple.utils.resource.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository constructor(
    private val remoteService: RemoteService,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPokemon(name: String): Resource<Pokemon> {
        return withContext(dispatcher) {
            try {
                val response = remoteService.getPokemon(name)
                val responseMapped = PokemonMapper().mapper(response)
                responseHandler.handleSuccess(responseMapped)
            } catch (e: Exception) {
                responseHandler.handleException(e)
            }
        }
    }

}
