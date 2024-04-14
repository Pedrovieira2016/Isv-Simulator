package vieira.apps.simulators.data

import vieira.apps.simulators.data.dao.IsvDao
import vieira.apps.simulators.safeApiCall
import javax.inject.Inject

class IsvRepository @Inject constructor(
    private val isvDao: IsvDao
) {

    fun fetchValuesByCylinderCapacity(cylinderCapacity: Int) = safeApiCall {
        isvDao.getValuesByCylinderCapacity(cylinderCapacity)
    }

    fun fetchValuesByFuelTypeAndEmissions(fuelType: Int, emissions: Int) = safeApiCall {
        isvDao.getValuesByFuelTypeAndEmissions(fuelType, emissions)
    }
}
