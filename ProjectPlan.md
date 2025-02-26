# *Project Plan*

### Week 3
- [x] Design & create event visualizer database
- [x] Screen Design
- [x] User stories (specify MVP) 
- [x] Project plan rough draft 

### Week 4
- [x] Create user table
- [x] User bean
- [x] Add Hibernate annotations to user bean
- [x] Add user mapping to Hibernate config 
- [x] Setup project w/ Log4j2 
- [x] Setup project w/ SessionFactory for handling database connection
- [x] Setup Generic Dao for handling database transactions
- [x] Write unit tests for user class w/ @ least 80% coverage (73% achieved)

### Week 5
- [x] Setup project with Bootstrap & JSTL
- [x] Create notebook & event tables
- [x] Notebook & event  beans
- [x] Add Hibernate annotations to notebook & event beans
- [x] Add notebook & event mappings to Hibernate config
- [x] Write unit tests for notebook & event classes

### Week 6
- [ ] Create ***homepage.jsp***
- [ ] Create details & notes & performer tables
- [ ] Details, performer & notes beans 
- [ ] Add Hibernate annotations details, performer & notes beans
- [ ] Add details, performer & notes mappings to Hibernate config
- [ ] Write unit tests for details, performer & notes classes
- [x] Setup AWS Elastic Beanstalk environment & database
- [x] Add AWS configurations to project
- [x] Deploy project to AWS

### Week 7
- [ ] Create Location table & bean
- [ ] Create POJO for Google Places Autocomplete (Places & SearchPlaces)
- [ ] Create places dao & searchPlaces dao 
- [ ] Map autocomplete response (JSON) to places and searchPlaces objects
- [x] Create user pool w/ AWS Cognito for user login & signup
- [x] Setup project with directories, configurations & files for Cognito implementation
- [x] Setup project to redirect to HTTPS to allow use of Cognito in an Elastic Beanstalk environment

### Week 8
- [ ] Setup project with REST app & add classes for managing resources
- [ ] Create service layer to perform CRUD operations on beans by calling DAO methods
- [ ] Implement Google Autocomplete places & search places into REST app (front end will retrieve data thru fetch calls)
- [ ] Test routes with cURL & document successful routes

### Week 9
- [ ] Test routes with cURL & document successful routes
- [ ] Create JS classes for entities
- [ ] Create fetch.js with generic/reusable methods for performing CRUD operations

### Week 10
- [ ] Work on frontend 

### Week 11
- [ ] Work on frontend

### Week 12
- [ ] Work on frontend

### Week 13
- [ ] Work on frontend

### Week 14
- [ ] Work on frontend

### Week 15
- [ ] Work on frontend

### Week 16
- [ ] Work on frontend / complete project<br><br>

--- 
# *Project Files*

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

