package vieira.apps.simulators.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vieira.apps.simulators.data.AppDatabase
import vieira.apps.simulators.data.dao.IsvDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val mDbName = "simulators.db"

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            mDbName
        ).createFromAsset("databases/$mDbName")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIsvDao(db: AppDatabase): IsvDao {
        return db.isvDao()
    }
}
