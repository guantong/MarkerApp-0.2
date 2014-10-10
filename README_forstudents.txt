MarkerApp version 0.1
---------------------
A prototype tool for marking student assignments according to assignment
marking schemes, and preparing formatted grading reports for distribution.


System Requirements:
---------------------
A computer that runs on JDK 1.6 or higher


Installation and running instructions:
---------------------
Open unzip the MarkerApp.zip file to a directory on your computer. 
Then, open package.bluej in the directory with BluJ. Run the main function
in the MarjerApp class.


Required Files to run MarkerApp:
---------------------
Assignment marking schemes and student lists are required to to use the 
application, and must be provided in Comma-Separated Value (CSV) format.

	* Student list file (e.g. "studlist1.txt" enclosed in the zip file): 
	Each line of the file should have the details for one student, including 
	their name, student number, and email address, separated by commas.

	* Assignment marking spec file (e.g. "assign1.txt" enclosed in the zip file): 
	The first line of the file should have the unit code for the assignment 
	The second line of the file should have the name of the assignment. 
	Each subsequent line relates to one assignment criterion, and includes 
	the name of the criterion, the maximum mark, and either "True" or "False", 
	indicating whether the marker is able to specify a textual comment 
	for each assignment submission relating to this criterion.

	
Using the application:
---------------------

MarkerApp is command-line driven.  To use the system, start the application
and enter one of these commands from the keyboard:


command    	Instructions
---------------------
q		quit the application.
p		print a status report indicating whether the assignment
		has been marked.
c afile sfile	create a new assignment, with assignment spec specified
		in afile and the student list in sfile
m		mark the current assignment
s assigfile	save the current assignment, including marks, to assigfile
l assigfile	load an assignment and marks from assigfile
r reportpath	create assignment reports and save them to the directory
		specified in reportpath.
	

