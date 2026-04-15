package com.example.pep;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Marks this class as a database entity.
// Room will create a table named "events" based on this class.
@Entity(tableName = "events")
// Declares the model representing a single event record
public class Event {

    // Defines the primary key for the table.
    // @PrimaryKey uniquely identifies each row
    // autoGenerate = true means Room automatically assigns a value
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String category;
    public String location;
    public String dateTime;
}