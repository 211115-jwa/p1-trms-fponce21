# Tuition Reimbursement Management System Description
The tuition reimbursement management system (TRMS) is an application that allows users to submit requests for reimbursement for different events/certifications/etc. Direct supervisors, department heads, and benefits coordinators must then approve or reject requests. The TRMS may also include login, file attachments, comments, and other features that support the approval process workflow. All features are tested with unit testing, API/integration testing, and end-to-end testing.

## Technologies Used
- Java 8
- JUnit 5
- Mockito 4
- Cucumber 7
- Selenium 4
- HTML 5 with CSS
- JavaScript EMCA 6

## Features
List of features ready and TODOs for future development
1. Employees can log in and out
2. Supervisors can log in and out
3. Employees can view their own submitted TRMS Reimbursement Requests
4. DAO JUnit tests are successful
5. Service JUnit/Mockito tests are successful
6. As an Employee I can submit a TRMS Reimbursement Request
  - To-Do List:
1. All TRMS Reimbursement Requests can be viewed
2. Supervisors can view their own submitted TRMS Reimbursement Requests
3. Front end must be built using HTML/CSS and JavaScript. Styling libraries like Bootstrap are allowed.
4. Supervisors can view The TRMS Reimbursement Requests They need to Approve/Deny
5. As a Supervisor I can Approve/Deny a TRMS Reimbursement Request
6. Get Cucumber/Selenium tests running successfully

## Getting Started
1. Go to your Documents folder
2. Make a new folder
3. Rename your new folder "TRMS"
- If You Have a Windows Computer
4. Open a new Command Prompt in your TRMS folder like this.
![image](https://user-images.githubusercontent.com/94322184/152011589-5d4d10b6-fd03-4d91-90e7-12f0b2c1845a.png)

- If You Have a Mac
4. Open a new terminal in your TRMS folder like this.
![image](https://user-images.githubusercontent.com/94322184/152011729-6212ff80-0b1c-46b0-89d4-fad73c4b24a3.png)

- Joint Instructions
5. Go to this project's GitHub page and clicking on the green code button.
![Git clone](https://user-images.githubusercontent.com/94322184/152012062-6cb16f17-000a-4006-bafc-f8a7c6257add.png)

6. Most of you want HTTPS
7. Click the double boxes to copy this command.

![githttps](https://user-images.githubusercontent.com/94322184/152012404-9223c748-abf4-4de4-adea-4cc92d2bdd9c.png)

- If You Have a Windows Computer
8. Go back to your command prompt that you opened in step 4.
- If You Have a Mac
8. Go back to your terminal that you opened in step 4.
- Joint Instructions
9. Type
    - git clone [paste url here]
- Now we check if you have Java and if you do, which version.
10. In your command prompt/terminal type:
  - java -version
11. It should look something like this.

![cmdjavac](https://user-images.githubusercontent.com/94322184/152013330-76c1280c-cf97-4320-84b3-d4b15b331a64.png)

13. If there is no return value or the version number is below 8 go here. Follow the instructions for your operating system.
14. Next you will install PostgreSQL from here. Follow the instructions for your operating system.
15. Install Eclipse from https://www.eclipse.org/downloads/packages/installer
16. Install DBeaver from https://dbeaver.io/download/
 - In step 9. you cloned the repository into your new folder TRMS. Now that you have Java, Postgres, Eclipse, and VS Code            installed on your system, you are going to populate your new SQL database and run the program.
17. Open Eclipse IDE for Enterprise Developers
18. Go to File -> Import -> Maven -> Existing Maven Projects
19. Browse for trms-back
20. Finish
- The SQL scripts are in src/main/resources
21. Open DBeaver
22. Go to File -> New
23. Select DBeaver -> Database Connection
24. Select the PostgreSQL Blue Elephant
25. Leave everything as it is
26. Check the connection, if not connecting, troubleshoot.
27. Finish
28. Make a database.properties in src/main/resources that looks similar to this:
![image](https://user-images.githubusercontent.com/94322184/152013780-fa8bf42e-85a4-46a8-947a-1722baa16fac.png)

29. Make a Schema called trms
30. Right click on trms, go to SQL Editor -> Open SQL Console
31. Copy the Script in DDL.sql into the SQL Console and run it.
32. Repeat step 29. then Copy the DML.sql into the new console and run it.
33. Expand src/main/java -> com.revature.app -> TRMSApp.java
34. Right click on TRMSApp.java
35. Select Run as -> Java Application
![trmsapprun](https://user-images.githubusercontent.com/94322184/152014984-953bc108-a1bf-42b2-9a11-a206674e59a1.png)

![javalin](https://user-images.githubusercontent.com/94322184/152015026-a0412d09-43d6-425d-98b3-850003fa42cb.png)

36. Start the App by going to the directory that you placed the TRMS App into, navigating to trms-front -> html
37. Open index.html in a web browser of your choice
38. You are ready to run the TRMS.

## License

Copyright (c) 2022 Francisco Antonio Ponce

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

