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

#### Week 5
This week's main tasks were working on the DaoService Test class, creating the Notebook table in my database, the Notebook
bean, mapping the bean with hibernate and testing the Notebook class with my CRUD REST endpoint through fetch calls on
the client side.

*Total time spent: 7 hours*

#### Week 6
I ran into some issues with my Notebook class working with my REST endpoint.<br><br>
I kept recieving this error:<br>
<mark>"user":{<ns1:XMLFault xmlns:ns1="http://cxf.apache.org/bindings/xformat">
<ns1:faultstring xmlns:ns1="http://cxf.apache.org/bindings/xformat">javax.json.stream.JsonGenerationException:
Invalid json</ns1:faultstring></mark><br>
My endpoint was returning a recursive loop when I was trying to retrieve a single Notebook by id. I got stuck on this for 
a little bit because I did have my bean classes mapped & annotated properly. I used my old project that I knew had mappings
that worked properly as a reference to try and fix the issue, but I kept receiving the error. I started fresh with a new 
directory to see if this would make the error go away, but it did not. I eventually thought to use ObjectMapper to return
the entity in my REST endpoint and that did the trick! I then went on to test the CRUD operations with fetch calls on the client
side and everything was working fine. I also reworked my DaoService class a bit to be more straightforward by preloading the entity
classes into a map in the constructor vs. adding the classes into a map depending on which entity was passed in as a path param.

*Total time spent: 10 hours*