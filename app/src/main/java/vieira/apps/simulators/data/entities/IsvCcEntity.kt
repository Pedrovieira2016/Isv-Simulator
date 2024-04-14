package vieira.apps.simulators.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ISVCC")
data class IsvCcEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "CylinderCapacityFrom")
    val cylinderCapacityFrom: Int,
    @ColumnInfo(name = "CylinderCapacityUntil")
    val cylinderCapacityUntil: Int,
    @ColumnInfo(name = "Rate")
    val rate: Float,
    @ColumnInfo(name = "PortionToDeduct")
    val portionToDeduct: Float
)
