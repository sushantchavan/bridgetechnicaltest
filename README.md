### Package Structure

#### 1. activity
...All the activity related class files are placed under this folder
#### 2. adapter
...Grouping all the adapter classes for listviews
#### 3. callback
...Manage different types of callbacks
#### 4. database
...Layer to create and manage the local database. All the database queries are executed here
#### 5. eventlisteners
...Extending event listeners
#### 6. manager
...Manager class to get fetch data
#### 7. model
...The modesl required for the application are declared and maintained here
#### 8. netwwork
...The web service calls are managed from here
#### 9. util
...General Utility functions

### Application Flow

- Check for internet connection
- If connectivity exists fetch the list of pupils and populate the list view
	- On Scroll fetch additional data pupils based on the total number of pages
	- On fetching the data insert the records in the local database
- If there is no internet connectivity check if there are any records in the local database
	- Populate the list view with the records from the local database
- On click of an element in the list view on the pupil details in different activity 

### Assumptions

- The value of totalPages in the api call /api/pupil?page=1 does not change as the value of page changes

### Libraries Used

1. Retrofit  - API Calls
2. RecyclerView - Listing the pupils data
3. Glide - Loading and displaying images
4. CircularImageView - Display the profile image in a circle
5. GSON Converter - Parsing the API response

