package com.example.assignment2.statusBar

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment2.statusBar.StatusDao

@Database(
    entities = [ProductEntity::class],
    version = 4
)
abstract class StatusDatabase : RoomDatabase(){

    abstract val dao: StatusDao

}