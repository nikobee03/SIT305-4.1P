package com.example.pep;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.pep.*;
import com.example.pep.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

// Defines a fragment that can be placed inside an activity.
public class AddEventFragment extends Fragment {

    // Declares a ViewModel reference. This is used to interact with the database indirectly.
    private EventViewModel vm;

    @Override
    // The lifecycle method is called to create the fragment’s UI.
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Loads the layout file and converts it into a usable View object.
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);

        // Connect UI elements from the layout to code:
        EditText title = v.findViewById(R.id.etTitle);
        EditText category = v.findViewById(R.id.etCategory);
        EditText location = v.findViewById(R.id.etLocation);
        EditText date = v.findViewById(R.id.etDate);
        Button save = v.findViewById(R.id.btnSave);

        // Initialises the ViewModel
        // Fragment→ViewModel→Repository→Database
        vm = new ViewModelProvider(this).get(EventViewModel.class);

        // The click handler defines what happens when the Save button is pressed.
        save.setOnClickListener(view -> {
            String t = title.getText().toString();
            String d = date.getText().toString();

            // Ensures required fields are filled. If not, a message is shown and execution stops.
            if (t.isEmpty() || d.isEmpty()) {
                Toast.makeText(getContext(), "Title & Date required", Toast.LENGTH_SHORT).show();
                return;
            }
            // Compares the entered date with today’s date so the user is prevented from saving past events.
            if (d.compareTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date())) < 0) {
                Toast.makeText(getContext(), "Date cannot be in past", Toast.LENGTH_SHORT).show();
                return;
            }
            // Initiates a new event
            Event e = new Event();
            // Assigns user input to the object
            e.title = t;
            e.category = category.getText().toString();
            e.location = location.getText().toString();
            e.dateTime = d;
            // Passes the event to the ViewModel, which stores it in the Room database.
            vm.insert(e);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        });
        // Returns the constructed UI so it can be displayed
        return v;
    }
}