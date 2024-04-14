package vieira.apps.simulators.data.usecase

import vieira.apps.simulators.FuelType
import vieira.apps.simulators.data.IsvRepository
import javax.inject.Inject

class FetchValuesByFuelTypeAndEmissionsUseCase @Inject constructor(
    private val repository: IsvRepository
) {

    operator fun invoke(fuelType: FuelType, emissions: Int) =
        repository.fetchValuesByFuelTypeAndEmissions(emissions = emissions, fuelType = fuelType.ordinal)
}
