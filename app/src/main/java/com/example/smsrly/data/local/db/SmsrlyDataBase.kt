package com.example.smsrly.data.local.db


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.smsrly.data.local.db.entities.RealEstateEntity
import android.content.Context
import com.example.smsrly.data.local.db.converters.RealEstateConverter

@Database(entities = [RealEstateEntity::class], version = 3, exportSchema = false)
@TypeConverters(RealEstateConverter::class)
abstract class SmsrlyDataBase : RoomDatabase() {
    abstract val smsrlyDataBaseDao: SmsrlyDataBaseDao

    companion object {
        @Volatile
        private var INSTANCE: SmsrlyDataBase? = null
        fun getInstance(context: Context): SmsrlyDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SmsrlyDataBase::class.java,
                        "SmsrlyDataBase"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}