package com.example.pep;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

// Marks this interface as a Data Access Object
@Dao
// Declares the contract of allowed operations
public interface EventDao {

    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    // Executes a custom SQL query to retrieve all rows and sort from earliest to latest
    @Query("SELECT * FROM events ORDER BY dateTime ASC")
    // Provides a list of events and makes it observable
    LiveData<List<Event>> getAllEvents();
}