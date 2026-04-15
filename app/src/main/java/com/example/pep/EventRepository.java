package com.example.pep;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Defines a repository class
// Its role is to manage data access and abstract the database from the rest of the app
public class EventRepository {

    // Holds a reference to the DAO, which performs actual database operations
    private EventDao dao;

    // Stores observable data from the database
    private LiveData<List<Event>> allEvents;

    // Creates a background thread executor. This is required because
    // database operations are not allowed on main (UI) thread
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    // Initialises the repository
    public EventRepository(Application app) {
        EventDatabase db = EventDatabase.getInstance(app);
        dao = db.eventDao();
        allEvents = dao.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void insert(Event event) {
        executor.execute(() -> dao.insert(event));
    }

    public void update(Event event) {
        executor.execute(() -> dao.update(event));
    }

    public void delete(Event event) {
        executor.execute(() -> dao.delete(event));
    }
}