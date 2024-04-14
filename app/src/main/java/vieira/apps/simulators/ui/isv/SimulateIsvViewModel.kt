package vieira.apps.simulators.ui.isv

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import vieira.apps.simulators.FuelType
import vieira.apps.simulators.data.usecase.FetchValuesByCylinderCapacityUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SimulateIsvViewModel @Inject constructor(
    private val valuesByCylinderCapacityUseCase: FetchValuesByCylinderCapacityUseCase,
//    private val valuesByFuelTypeAndEmissionsUseCase: FetchValuesByFuelTypeAndEmissionsUseCase
) : ViewModel() {

    private val _isvUIResult = MutableStateFlow<ISVUiResult>(ISVUiResult.Loading)
    val isvUIResult = _isvUIResult.asStateFlow()

    fun simulateIsv(
        cylinderCapacity: String?,
        co2Emissions: String?,
        fuelType: FuelType?,
        dateOfFirstRegistration: Date?
    ) {
//        //Exemplo para um carro de 998cm3: 998cm3 * 1,09€ - 849,03€ = 238,79€ (em 2023 era 229,32€)
//        viewModelScope.launch(Dispatchers.IO) {
//            Log.e(
//                TAG,
//                "OKlog simulateIsv $cylinderCapacity $co2Emissions $fuelType $dateOfFirstRegistration"
//            )
//            valuesByCylinderCapacityUseCase.invoke(cylinderCapacity).collect {
//
//            }
//            combine(
//                valuesByCylinderCapacityUseCase.invoke(cylinderCapacity),
//                valuesByFuelTypeAndEmissionsUseCase.invoke(fuelType, co2Emissions)
//            ) { cylinderCapacity, fuelTypeAndEmissions ->
//                Log.e(TAG, "OKlog simulateIsv $cylinderCapacity $fuelTypeAndEmissions")
//            }.catch {
//                Log.e(TAG, "OKlog simulateIsv $it")
//                ISVUiResult.Error(it.message ?: "Error")
//            }.collect {
//                Log.e(TAG, "OKlog simulateIsv $it")
//            }
//        }
    }

    private fun handleException(it: Throwable) {

    }

    companion object {
        private const val TAG = "SimulateIsvViewModel"
    }
}

sealed interface ISVUiResult {
    data object Loading : ISVUiResult
    data class Success(val value: Double) : ISVUiResult
    data class Error(val message: String) : ISVUiResult
}
