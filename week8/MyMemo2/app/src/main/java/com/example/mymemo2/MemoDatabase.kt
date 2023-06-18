package com.example.mymemo2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Data::class], version = 1)
abstract class MemoDatabase : RoomDatabase(){
    abstract fun memoDao() : MemoDao

    companion object {
        private var instance: MemoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MemoDatabase? {
            if (instance == null) {
                synchronized(MemoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo-database"
                    ).allowMainThreadQueries().
                    build()
                }
            }
            return instance
        }
    }
}
//.addMigrations(MIGRATION_1_2).build()
val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 이전 버전과 새로운 버전 간의 마이그레이션 로직 작성
        // database.execSQL("ALTER TABLE ...");
        database.execSQL("ALTER TABLE Data ADD COLUMN memoHeart INTEGER NOT NULL DEFAULT 0");
    }
}

