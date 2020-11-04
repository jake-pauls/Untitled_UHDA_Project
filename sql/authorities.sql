DROP TABLE IF EXISTS authorities;

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