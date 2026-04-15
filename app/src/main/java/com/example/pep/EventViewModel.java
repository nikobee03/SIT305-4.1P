package com.example.pep;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pep.Event;
import com.example.pep.EventRepository;

import java.util.List;

// Defines a ViewModel that is tied to the application context.
// AndroidViewModel is used instead of ViewModel because it needs access to Application
public class EventViewModel extends AndroidViewModel {

    // Holds a reference to the repository, which performs data operations
    private EventRepository repo;

    // Stores observable event data
    private LiveData<List<Event>> events;

    // Initialises the ViewModel and passes the application context to the parent class
    public EventViewModel(@NonNull Application application) {
        super(application);
        repo = new EventRepository(application);
        events = repo.getAllEvents();
    }

    // Provides access to the event list for the UI
    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public void insert(Event e) { repo.insert(e); }
    public void update(Event e) { repo.update(e); }
    public void delete(Event e) { repo.delete(e); }
}