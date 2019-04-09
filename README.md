# Final-Project

Using Spring SpringMVC Mybatis and Shiro frameworks in the backend(RESTful), HTML, css(bootstrap) and javascript(Ajax) in the frontend.

The goal of this project is to develop a new web platform for the computer science department of MU to organise and schedule final year projects.

Included three use case tests:
1. Student submit their level of interests and query the result of project and utilize ccs system.
2. Lecturers designate candidate to do a certain project and cancel.
3. The operations of managing projects for lecturers.

Features: 
1. New Student backend
The new student backend is associated with the lecturer’s backend, once a lecturer designates a student, that student can query the result online. The entire process can be done by the network rather than as before using email to make contact with a lecturer to make sure. It improves student experience of project selection and saves time, and the student does not need to meet the lecturer.
2. More functionality on Lecture backend
The new backend for lecturers provides more functionality when managing projects or students. It is easy to get access to every candidate on each project. It will display a candidates table under that project and load student number and his interest level. The lecture can choose him/her or cancel to do a certain project via this table and is able to edit this project and add some notes or post some reference or change its status to draft or available.
3. Separation of the frontend and backend
The separation of frontend and backend improves the efficiency and maintainability of the system. It decouples the frontend and the backend and uses JSON to communicate which makes it easy to find errors if the system fails. In the meantime, it increases some stress to the frontend but relieves stress on the backend.
4. CCS category system
Every project should be classified to one certain domain, which helps students and the administrator to easily find projects and manage projects. Some students are interested in a particular domain, like web applications development, and just want to do the project in this domain. This category system is able to help them retrieve projects in the database efficiently and effectively.
