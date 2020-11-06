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
