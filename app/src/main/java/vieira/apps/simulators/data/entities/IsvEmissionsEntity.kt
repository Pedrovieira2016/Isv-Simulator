package vieira.apps.simulators.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ISVCO2")
data class IsvEmissionsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "Type")
    val type: Int,
    @ColumnInfo(name = "CO2From")
    val cylinderCapacityFrom: Int,
    @ColumnInfo(name = "CO2Until")
    val cylinderCapacityUntil: Int,
    @ColumnInfo(name = "Rate")
    val rate: Float,
    @ColumnInfo(name = "PortionToDeduct")
    val portionToDeduct: Float
)
