package vieira.apps.simulators.data

import androidx.room.Database
import androidx.room.RoomDatabase
import vieira.apps.simulators.data.dao.IsvDao
import vieira.apps.simulators.data.entities.IsvCcEntity
import vieira.apps.simulators.data.entities.IsvEmissionsEntity

@Database(
    entities = [IsvCcEntity::class, IsvEmissionsEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun isvDao(): IsvDao
}
