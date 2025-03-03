~~### [Home](/README.md)  •  [Project Plan](/ProjectPlan.md)  •  [Design Files](/DesignFiles.md)  •  [User Stories](/UserStories.md)

# *Design Files*

<details>

<summary>Event Visualizer EER Diagram</summary>

*Event Visualizer Schema*
![Database diagram](/images/event_visualizer_EERD.png)


</details>


<details>

<summary>Screen Design</summary>

***Landing Page***
![Event Visualizer landing page with sign in and sign up options](/images/screens/landing_page.png)<br><br><br><br>

***Homepage***<br>
![Event Visualizer homepage with a video display](/images/screens/homepage.png)<br><br><br><br>

***View All Events***<br>
![View all events screen](/images/screens/view_my_events.png)<br><br><br><br>

***Create New Event***<br>
![Create new event screen](/images/screens/add_event.png)<br><br><br><br>

***Edit Event***<br>
![Edit events screen](/images/screens/edit_event.png)<br><br><br><br>

***Events Calendar***<br>
![Sign up modal with personal information fields](/images/screens/events_calendar.png)<br><br><br><br>

***About Us***<br>
![About us screen](/images/screens/about_us.png)<br><br><br><br>

***Choose Destination for New Event***<br>
![Choose notebook for event screen](/images/screens/custom_field.png)<br><br><br><br>

***Confirm Changes to Details***<br>
![Confirm editing changes screen](/images/screens/confirm_changes.png)<br><br><br><br>

</details>

<details>

<summary>Project File Structure</summary>

### 〚***Servlets***〛

- **ApplicationStartup**<br>
  Puts SessionFactory, Hibernate config in servlet context / app scope<br><br>
- **NavigationServlet**<br>
  handles forwarding navigation items to jsp<br><br>
- **Login**<br>
  handles user login or signup<br><br>
- **Auth**<br>
  checks login credentials or signup for accessing site features<br>

### 〚***JSP***〛

- index.jsp<br>
- login.jsp<br>
- homepage.jsp<br>
- add-event.jsp<br>
- edit-event.jsp<br>
- delete-event.jsp<br>
- about-us.jsp<br>
- calendar.jsp<br>
-
- error-page.jsp

### 〚***JavaScript***〛

- **fetch.js**<br>
  generic methods for get, post, delete, & put fetch calls<br><br>
- **notebook.js**<br>
  notebook class<br><br>
- **event.js**<br>
  event class<br><br>
- **location.js**<br>
  location class<br><br>
- **details.js**<br>
  details class<br><br>
- **notes.js**<br>
  notes class<br><br>


### 〚***Notes + Things to Look Into / Incorporate***〛

- emailJS | send email directly from code
- Mockaroo | mock data for database

</details>


