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