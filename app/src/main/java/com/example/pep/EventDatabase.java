package com.example.pep;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Tells Room how to build the database
//entities = {Event.class}: the database will contain a table based on the Event class
@Database(entities = {Event.class}, version = 1, exportSchema = false)
// Declares a database class that extends RoomDatabase
public abstract class EventDatabase extends RoomDatabase {

    // Defines how to access the DAO
    public abstract EventDao eventDao();

    // Declares a single shared instance of the database
    private static EventDatabase instance;

    // Provides global access to the database.
    // static: can be called without creating an object
    // synchronized: ensures thread safety
    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    EventDatabase.class,
                    "event_db"
            ).build();
        }
        return instance;
    }
}