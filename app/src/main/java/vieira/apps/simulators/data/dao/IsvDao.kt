package vieira.apps.simulators.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vieira.apps.simulators.data.entities.IsvCcEntity
import vieira.apps.simulators.data.entities.IsvEmissionsEntity

@Dao
interface IsvDao {

    @Query("SELECT * FROM ISVCC WHERE CylinderCapacityFrom <= :cylinderCapacity AND CylinderCapacityUntil >= :cylinderCapacity")
    fun getValuesByCylinderCapacity(cylinderCapacity: Int): Flow<IsvCcEntity>

    @Query("SELECT * FROM ISVCO2 WHERE :fuelType == Type AND CO2From <= :emissions AND CO2Until >= :emissions")
    fun getValuesByFuelTypeAndEmissions(fuelType: Int, emissions: Int): Flow<IsvEmissionsEntity>
}
