# Reflection Journal 

#### *Week 1*
I didn't have to do much aside from join the class Slack channel and accept the invite for the class repo. I was already set up
with all the necessary configurations from taking this class during a previous semester.

*Total time spent: 30 mins*

#### *Week 2*
This was also a pretty easy week for me. Our weekly assignment dealt with creating a User Bean and a Servlet to handle 
requests from the client. The servlet handled a 'Get All Users' request and also the ability to handle searching for a user
by last name. I didn't run into any issues.

*Total time spent: 1 hour*

#### *Week 3*
During this week I figured out how I wanted to rework my indie project from last year / simplify it. I incorporated log4j2 
into my indie project and added my project details to the student repo.
I also worked on the Inverse Captcha assignment which was mostly a review for me.

*Total time spent: 1 hour*

#### *Week 4*
This week I caught up & turned in my missing assignments. I also spent a decent amount of time working on my indie project.
I created a unit test for my User class using the GenericDao methods. I added a REST app to my directory and created
an endpoint to handle CRUD operations. I created a GenericDao Service class to serve as the layer between
the GenericDao and REST endpoint. I wanted the service layer methods to be generic to reduce the amount of code / redundancy.
I was able to do this by passing in the entity name as a path parameter and implementing search methods for the necessary entity
depending on the path param. I created a quick JSP page with a user form and started working through the CRUD operations 
one by one to make sure they were working correctly. I got everything to work for add, delete, get operations--so still need
to work on the update method which I will hammer out tomorrow.

*Total time spent: 4 hours*