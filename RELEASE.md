# v0.3 RELEASE - Untitled

### Release Notes - Iteration 3

#### Core Features

* **Hardware Requests:**  
	* Allows help desk employees and admins to assign hardware from a hardware ticket
		* only an admin or the employee assigned to the ticket can assign hardware from the ticket  
		* Ticket must be assigned  
		* Ticket must be a hardware ticket  
		* Can only assign hardware listed in the Hardware Types List
	* View and add hardware to hardware type list
		* From the hardware type list can add new hardware types so they can later be assigned
	* update assigned hardware to returned or lost
		* From the hardware assignement list can make the hardware as lost or returned

* **Commenting:**
    * Allows users and employees to communicate on tickets
        * Comments can be written on the tickets when they are assigned to an employee
        * Comments can be deleted by the author
    * Sends an email to the other user to alert them of a new comment on a ticket they created or were assigned too.
        * Email will only be sent to the user who didn't write the comment

* **Slack Integration:**
    * Users of any type can now associate their Slack account with the UHDA
        * Upon logging in, the UHDA will check if a user's UHDA email address is present in the Slack workspace
        * UI alerts and notifications are present so that the user knows whether their association was successful
    * Custom notifications are sent to the employee Slack channel whenever a user creates a new ticket, alerting employees that it is ready for pickup
    * Notifications are directly messaged to all user's connected with Slack whenever their tickets are updated, such as:
        * When an employee initially picks up a ticket
        * When ticket assignment (`Assigned To`) is updated
        * When ticket `Status` is updated
        * When ticket `Priority` is updated

#### Supplemental Updates

* **Ticketing UI Rework:**
    * User ticketing UI is updated with two different types of views, a card view and a list view
        * The card view displays the user's 6 most recently created tickets for their convenience
        * The list view is a sortable list that displays all of the user's tickets
    * Employee ticketing UIs are also updated with two different types of views, a card view and a list view (for both assigned and available tickets)
        *  Card views on pages display the highest priority tickets and the most recently added tickets respectively
        *  List views on both pages are similar to the user ticketing UI, displaying a comprehensive list of all tickets in the given category
  
* **Priority Updates:**
    * The `Priority` field is now inaccessible to user's when creating a new ticket
        * Instead this field is automatically generated to `Under Review`
        * Any decisions, assignments, or creations or priority-based cards is completely dependent on employee and administrator users

# v0.2 RELEASE - Untitled

### Release Notes - Iteration 2

#### Core Features

* **Basic Ticketing Functionality:**  
    + Help desk tickets can be created from the User home page
        - Ticket fields include Title, Description, Priority, and Category  
           * Tickets can be set with a Priority of _Trivial_, _Low_, _Normal_, _High_, and _Critical_
           * Tickets can be set with a Category of _General_, _Software_, _Hardware_, _Internet_, or _Installation Request_
    + Help desk tickets can also be created from the Employee home page 
        - All created tickets _(regardless of being created by a User, Employee, or Administrator)_ are immediately added into the Employee ticket queue
        - Employees and admins can 'Pickup' or 'Open'/Edit fields on newly created tickets (see _Ticket Actions_ below for more information)
    + All created tickets have a unique colour for their _Priority_ or their Assignment Status (see _Ticketing Display_ below for more information)  

* **Ticketing Display:**  
    + Display the user home page 
        - Tickets can be sorted from this view
        - Tickets can be opened from this view
        - All created tickets of the logged in user are displayed to the user
        - There is a tab allowing navigation between ticket view and the create ticket form
    + If the user is an employee they will be shown a link to the employee page
        - Displays all assigned tickets to the logged in employee
        - Displays all unassigned tickets in another tab 
        - Tickets can be picked up from the unassigned tickets tab
        - Tickets can be opened from either tab
        - Tickets can be sorted from either tab
    + If the user is an admin, they will be shown a link to the admin page  

* **Ticket Actions:**  
	* Allows help desk employees and admins to assign ticket  
		* only an admin or the employee a ticket is assigned to can reassign a ticket
		* Anyone employee can assign a unassigned ticket. 
		* an email is sent to the author/creator of the ticket, and the person who is assigned is cc'ed
	* update the status of a ticket
		* an email is sent to the author/creator of the ticket, and the person who is assigned is cc'ed
	* update the priority of a ticket
		* an email is sent to the author/creator of the ticket, and the person who is assigned is cc'ed  
  
#### Supplemental Updates

* **UHDA UI Rework:**
    + Entire UI is redesigned with a sleek, robust, and intuitive feel
    + Page layouts and routing re-configured with the end-user experience in mind

#### Resolved Defects

* **Updated User Login and User Authentication:**
     + User Login and Authentication implemented with UI rework in mind
          * Validation, for both success and failure results in secure authentication and clean UI messages
     + Utilizes an updated and relevant authentication approach to make User and Employee use throughout the UHDA smoother

# v0.1 RELEASE - Untitled

### Release Notes - Iteration 1

* **Login/logout functionality:**  
	* Login function added to login page
        * If the username doesn't exist or the password is wrong, an error message is displayed
    * Register function added with link "register here" on login page
        * All fields are required for the register form
        * If an account with the same username exists, an error message will be displayed(same with email)
        * Account is then added to the database and the user can log in with their credentials 
  
* **Password Reset:**  
	* Password reset feature added with link on the log in page for "forgot password?"  
	* upon clicking "Forgot Password?" the user is brought to a forgotpassword view  
		* on this view they are prompted for a enter a valid email address  
		* if the email they enter is not a valid user an error is resturned  
		* if it is a valid users email, a password reset token is generated and added to their profile, along with a reset link email sent to the user  
	* When password reset email is received (which contains their reset token) upon clicking the reset link they are brought to the password reset form  
		* user enters new password and clicks submit, their new password is recorded and they are brought to the login page  
  
* **Admin-Side User Management:**  
	* 'Admin-Side User Management' tab has been added and is accessible from the user home page, should the logged in user have administrator permissions
        * Once open, the administrator can view all users in their workspace and has the ability to perform multiple operations on the displayed data, without leaving or navigating away from that page
		* For security reasons, an administrator cannot view all the fields associated with each user
	* Administrators can perform edits to current user information including their first name, last name, email, and role within the workspace
		* Usernames, passwords, security questions, and security answers associated with a user cannot be modified by an administrator
		* NOTE: Administrators are the only users allowed to elevate or change the roles of other users
	* Administrators can create new users and add them to the workspace
		* Once created, a user will receive an email to confirm their account and set their password within their workspace
			* This password confirmation procedure occurs in a very similar way to our 'Password Reset' feature, please see the 'Password Reset' section for more details
	* Administrators can remove pre-existing users from the workspace
		* Once removed, the user will be unable to log-in and will have to re-register in order to regain access into the workspace