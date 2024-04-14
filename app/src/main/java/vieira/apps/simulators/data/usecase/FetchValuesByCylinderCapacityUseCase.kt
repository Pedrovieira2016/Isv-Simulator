package vieira.apps.simulators.data.usecase

import vieira.apps.simulators.data.IsvRepository
import javax.inject.Inject

class FetchValuesByCylinderCapacityUseCase @Inject constructor(
    private val repository: IsvRepository
) {

    operator fun invoke(cylinderCapacity: Int) =
        repository.fetchValuesByCylinderCapacity(cylinderCapacity)
}
