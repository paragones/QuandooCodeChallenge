# QuandooCodeChallenge
An Android Source Code for QuandooCodeChallenge. 

The App Workflow :
The user will open and the app and he/she will see a list of customers with their First names and Last names. 
The user can search a customer thru their last names. Afterwards, the user will then be shown to another activity. 
This activity will show two columns with different cells so the user can choose which free table he/she can put the customer to. 
The user can then click back and choose more customers to be allocated to other tables. After 10 minutes, whether the app was
closed or not, all the allocated tables will be freed up. 

Fufilled Requirements :

Requirement:

1. Tables have to be represented as cells on grid

2. Available and unavailable tables have to be easily recognized

          -- Tables are shown in each cell showing the sign "Free". If not, customers name will be shown with a different back ground. 

3. The app have to be able to work offline

        -- APis are initially loaded offline. Updated features can have online updates for customers or tables if needed. 

4. It would be great to have search option for customers

        -- The user can search for customers using their last names. A search field is provided

5. Use MVC/MVP/MVVM

        -- A mixture of MVC/MVP was used. Some Service handler classes are used for both Activities but sometimes, there are specific 
        views for each classes

6. At least one unit test

7. At least one instrumentational test

        -- Tests were created

8. Gradle

        -- Gradle were created


BONUS FEATURES

        -- ONE CUSTOMER FOR ONE TABLE : The user can choose one table for a customer. Once the user clicks another table for the customer, 
        the table will move to the next cell and remove the previous one. 
        
        -- ALLOCATE MANY CUSTOMERS : The user can click back and choose another customer. Then, that customer can be allocated for that table
        
        -- RESERVED TABLES : Once the user already chose a table for a customer, the user won't be mistakenly reallocated it to another 
        customer should she/he clicked the reserved by mistake. 

        -- REALLOCATE THE SAME CUSTOMERS : The user can reallocate the same customer if the user chose the same customer again. 

        -- CUSTOMER RESERVATIONS WERE SAVED : Even if the user closes the app, the reserved tables with its corresponding users are still there
        as long as it is before 10 mins. 
