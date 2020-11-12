-- Test Table Definitions and Insert Data

-- Users
CREATE TABLE users(
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(100),
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	email VARCHAR(50),
	security_question VARCHAR(60),
	security_answer VARCHAR(20),
	reset_token VARCHAR(50),
	role VARCHAR(20)
);

INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role)
	VALUES('admin', '$2a$10$j/x9DhaoTtpET9peZp9AOe1EhQQfscXvVj7FJ5QdzDe/Wyzrt/XfK', 'Tyler', 'Kronk', 'admin@UHDATesting.com', 'What is the name of your first pet?', 'Bowser', 'admin' ),
		  ('employee', '$2a$10$U3LA8nuobCKh9mN67/MXmu8D9yT0d3rFZsi/j77R93lq27jBzzHou', 'Gillian', 'Bunter', 'employeeeG@UHDATesting.com', 'What is the name of your first pet?', 'Banjo', 'employee' ),
		  ('user', '$2a$10$d6JTmNpwizj2usF7ro751OMP3sWfey4THaORUgrcgK9zKxzFYVt7q', 'Nick', 'Young', 'user@UHDATesting.com', 'What is the name of your primary school?', 'Apleton', 'user' );

-- Authorities
CREATE TABLE authorities (
	username VARCHAR(30) NOT NULL,
	authority VARCHAR(30) NOT NULL,
	FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO authorities (username, authority)
	VALUES ('admin', 'ROLE_ADMIN'),
		   ('employee', 'ROLE_EMPLOYEE'),
		   ('user', 'ROLE_USER');

-- Tickets
CREATE TABLE tickets(
    ticketId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
	title VARCHAR(40) NOT NULL ,
    description VARCHAR(1500) NOT NULL ,
	priority VARCHAR(30) NOT NULL ,
	status VARCHAR(30) NOT NULL ,
	username VARCHAR(30) NOT NULL ,
	assignee VARCHAR(30),
	category VARCHAR(30),
    dateOpened TIMESTAMP NOT NULL ,
    dateClosed TIMESTAMP,
	lastUpdated TIMESTAMP,
    FOREIGN KEY(username) REFERENCES users(username),
	FOREIGN KEY(assignee) REFERENCES users(username)
);

INSERT INTO tickets (title, description, priority, status, username, assignee, category, dateOpened, dateClosed, lastUpdated) 
		VALUES ('Test Ticket #1', 'Test Ticket #1 Description', 'Critical', 'In Progress', 'user', 'employee', 'Internet', '2019-12-07 06:42:21', null, '2020-03-03 09:09:38'),
			   ('Test Ticket #2', 'Test Ticket #2 Description', 'Low', 'Closed', 'user', 'employee', 'Software', '2020-03-11 19:43:45', '2019-11-10 17:54:37', '2019-11-10 17:54:37'),
			   ('Test Ticket #3', 'Test Ticket #3 Description', 'High', 'In Progress', 'user', 'employee', 'Installation Request', '2020-02-10 05:18:21', null, '2020-09-24 04:21:54'),
			   ('Test Ticket #4', 'Test Ticket #4 Description', 'Medium', 'Closed', 'user', 'employee', 'Hardware', '2020-03-11 03:36:42', '2020-08-11 11:23:22', '2020-08-11 11:23:22'),
			   ('Test Ticket #5', 'Test Ticket #5 Description', 'Trivial', 'Resolved', 'user', 'employee', 'Internet', '2020-04-28 12:08:24', null, '2020-08-21 10:30:17');