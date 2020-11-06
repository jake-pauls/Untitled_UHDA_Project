-- Users
DROP TABLE IF EXISTS users;

CREATE TABLE users(
	username VARCHAR(30) PRIMARY KEY,
	password VARCHAR(100),
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	email VARCHAR(50),
	security_question VARCHAR(60),
	security_answer VARCHAR(20),
	reset_token VARCHAR(50),
	role VARCHAR(20),
);


/*
	The following statements insert hashed passwords into the database using a salted BCrypt hashing algorithm
 	Since this is all sample data, use the login information below be permitted access to the application:
 		(USERNAME, PASSWORD)
		(admin, a)
		(employee, e)
		(user, u)
		(kylePanzonee99, password)
		(catFan, iluvcats)
		(rockerdude96, ripVan)
		(brunogood, isnice)
		(not_emily_here, nopers)
*/
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) 
	VALUES('admin', '$2a$10$j/x9DhaoTtpET9peZp9AOe1EhQQfscXvVj7FJ5QdzDe/Wyzrt/XfK', 'Tyler', 'Kronk', 'tKronker@gmail.com', 'What is the name of your first pet?', 'Bowser', 'admin' ),
		  ('employee', '$2a$10$U3LA8nuobCKh9mN67/MXmu8D9yT0d3rFZsi/j77R93lq27jBzzHou', 'Gillian', 'Bunter', 'bunterG@gmail.com', 'What is the name of your first pet?', 'Banjo', 'employee' ),
		  ('user', '$2a$10$d6JTmNpwizj2usF7ro751OMP3sWfey4THaORUgrcgK9zKxzFYVt7q', 'Nick', 'Young', 'NickYoung89@hotmail.com', 'What is the name of your primary school?', 'Apleton', 'user' ),
		  ('kylePanzonee99', '$2a$10$yWQXNA/qbqpVoYo02htr0e95rngl64Ar59AchVvIDNhfrsq6DNBVC', 'Kyle', 'Cork', 'Korker@gmail.com', 'What is the name of your first pet?', 'Bruno', 'user' ),
		  ('catFan', '$2a$10$4x.DYsIRzx8mq.EJH1lhdeeIRo2X8fkuoPd7OHjQf6JDreKP/6iHS', 'Karen', 'Groose', 'karencats@gmail.com', 'What is your favorite movie?', 'Cats', 'user' ),
		  ('rockerdude96', '$2a$10$MwidjcNqFIGzmRY2rxEREe33HLP1YXacUCmC1u9vhcdEzReeRJHdO', 'Evan', 'Werk', 'werkit@gmail.com', 'What is the name of your first pet?', 'Milo', 'user' ),
		  ('brunogood', '$2a$10$JMCPVgUjVYCKvdBRZKVo6OjvWiYKU.XFNILfhLYU9lJm/Axlvn79W', 'Bruno', 'Laklochloheskevsky', 'buno@gmail.com', 'What is the name of your first pet?', 'Dog', 'user' ),
		  ('not_emily_here', '$2a$10$jAoDdnid4bpUpD62JsAVIOeJ9lmgvJdCB0uVl42NRp8J.nwyhWLOK', 'Emily', 'Dolittle', 'talks2animals@gmail.com', 'What is your favorite movie?', 'Beetlejuice', 'user' );

		  DROP TABLE IF EXISTS authorities;

-- Authorities
CREATE TABLE authorities (
	username VARCHAR(30) NOT NULL,
	authority VARCHAR(30) NOT NULL,
	FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO authorities (username, authority) 
	VALUES ('admin', 'ROLE_ADMIN'),
		   ('employee', 'ROLE_EMPLOYEE'),
		   ('user', 'ROLE_USER'),
		   ('kylePanzonee99', 'ROLE_USER'),
		   ('catFan', 'ROLE_USER'),
		   ('rockerdude96', 'ROLE_USER'),
		   ('brunogood', 'ROLE_USER'),
		   ('not_emily_here', 'ROLE_USER');

-- Tickets		   
DROP TABLE IF EXISTS tickets;

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

INSERT INTO tickets(username, assignee, title, description, status, dateOpened, priority) 
	VALUES ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none'),
		   ('user','employee','Test Ticket', 'Hello employee, I am writing to confirm that my tickets are' ||
			'being made successfully. If there is an issue please add a comment','waiting','2020-10-01 01:01:01','none');

-- Comments
DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
    commentId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    ticketId INT NOT NULL ,
    author VARCHAR(30),
    value VARCHAR(800),
    dateCreated TIMESTAMP NOT NULL ,
    timeFinished TIMESTAMP NOT NULL ,
    FOREIGN KEY(ticketId) REFERENCES tickets(ticketId)
);

-- Queries
SELECT * FROM users;
SELECT * FROM authorities;
SELECT * FROM tickets;
SELECT * FROM comments;