# Personal Event Planner App
## Overview

The Personal Event Planner App is an Android application designed to help users manage their events, appointments, and schedules. It provides a simple interface to create, view, update, and delete events while ensuring that all data is stored locally on the device using the Room database.

The application follows modern Android development practices, including the use of Fragments, ViewModel, LiveData, and Jetpack Navigation.

## Features
### Core Functionality (CRUD)
- Create: Add new events with title, category, location, and date
- Read: View all events in a list sorted by date
- Update: Edit existing events via a dialog interface
- Delete: Remove events from the list
### Data Persistence
- Uses Room Database for local data storage
- Data persists after app closure and device restart
### Navigation
- Implemented using Jetpack Navigation Component
- Includes a Bottom Navigation Bar to switch between event List screen and add Event screen
- Uses Fragments instead of multiple activities
### Validation and Error Handling
- Prevents saving events with empty Title or Date
- Prevents selecting dates in the past
- Displays feedback using Toast messages
