package com.example.pep;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.pep.*;
import com.example.pep.Event;
import java.util.ArrayList;
import java.util.List;

// Defines a fragment responsible for showing a list of events
public class EventListFragment extends Fragment {

    // vm: handles data operations
    // adapter: converts event data into list items for display
    private EventViewModel vm;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_list, container, false);

        // Gets reference to the ListView that will display events
        ListView listView = v.findViewById(R.id.listView);
        // Initialises the ViewModel
        vm = new ViewModelProvider(this).get(EventViewModel.class);

        // Starts observing the event list
        // Updates automatically when the database changes
        vm.getEvents().observe(getViewLifecycleOwner(), events -> {
            // Converts each Event into a display string.
            List<String> displayList = new ArrayList<>();
            for (Event e : events) {
                displayList.add(e.title + " - " + e.dateTime);
            }
            // Binds the formatted data to the ListView
            adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    displayList
            );
            listView.setAdapter(adapter);
        });
        // Runs when a user taps an event
        listView.setOnItemClickListener((parent, view, position, id) -> {
            List<Event> currentEvents = vm.getEvents().getValue();
            if (currentEvents == null) return;

            Event selectedEvent = currentEvents.get(position);

            String[] options = {"Update", "Delete"};
            // Creates a popup dialog
            new AlertDialog.Builder(getContext())
                    .setTitle("Choose Action")
                    // Handles user selection
                    .setItems(options, (dialog, which) -> {
                        // If "Update" is selected, opens edit dialog
                        if (which == 0) {
                            showUpdateDialog(selectedEvent);
                        } else {
                            // If "Delete" is selected event removed from DB & UI updates automatically
                            vm.delete(selectedEvent);
                            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        }

                    })
                    .show();
        });

        return v;
    }

    // Handles editing an existing event
    private void showUpdateDialog(Event event) {
        // Loads a layout for editing
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_update_event, null);

        // Gets references to input fields
        EditText title = dialogView.findViewById(R.id.etTitle);
        EditText category = dialogView.findViewById(R.id.etCategory);
        EditText location = dialogView.findViewById(R.id.etLocation);
        EditText date = dialogView.findViewById(R.id.etDate);

        // Pre-fill existing data
        title.setText(event.title);
        category.setText(event.category);
        location.setText(event.location);
        date.setText(event.dateTime);

        // Creates the update dialog
        new AlertDialog.Builder(getContext())
                .setTitle("Update Event")
                .setView(dialogView)
                .setPositiveButton("Update", (dialog, which) -> {

                    String t = title.getText().toString();
                    String d = date.getText().toString();

                    if (t.isEmpty() || d.isEmpty()) {
                        Toast.makeText(getContext(), "Title & Date required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    event.title = t;
                    event.category = category.getText().toString();
                    event.location = location.getText().toString();
                    event.dateTime = d;

                    vm.update(event);
                    Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}