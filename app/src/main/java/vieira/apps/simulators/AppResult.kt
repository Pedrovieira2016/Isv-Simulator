package vieira.apps.simulators

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


sealed class AppResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : AppResult<T>()
    data class Error(val exception: Exception) : AppResult<Nothing>()
}

fun <T : Any> safeApiCall(apiCall: suspend () -> T): Flow<AppResult<T>> = flow {
    try {
        emit(AppResult.Success(apiCall.invoke()))
    } catch (e: Exception) {
        emit(AppResult.Error(e))
    }
}
