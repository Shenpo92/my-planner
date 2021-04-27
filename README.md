# my-planner
## MVP of an event scheduling app writter in Android Kotlin.

In this project we focus on the architecure of the code. We chose the MVVM pattern to design our project structure.
Below is a diagram of the application: 


![Alt text](./app/src/main/assets/planner_MVVM_schema.png?raw=true "MVVM Diagram")


### Issues encountered: 
- Getting the data from JSON file located in _assets_ folder.
- Retriving the correct Event in the Event recycle view and displaying the information correct information on the Event Detail page.

### Third party/Google libraries: 
- GSON (for parsing Json data)
- LifeCycle (for LiveData, in order to observe changes in the Data from the Activities)
- CardView (To represent each item on the RecylerView as a card)

### Improvement ideas
- Persist data using something like Amplify Data or Android SQLite
- Be able to create/modify/delete an event
- Connect app to API to handle data instead of a local Json file
- Improve UI
