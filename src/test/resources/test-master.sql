-- @author Jacob Pauls Student ID 300273666
-- @date Dec 2, 2020
-- test-master.sql
-- CSIS 3275 Group Project
-- Group Name: Untitled

-- Simulates the master table for testing

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS slack_association;
DROP TABLE IF EXISTS hardwareType;
DROP TABLE IF EXISTS hardwareAssignment;

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


INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) 
	VALUES('admin', '$2a$10$j/x9DhaoTtpET9peZp9AOe1EhQQfscXvVj7FJ5QdzDe/Wyzrt/XfK', 'Tyler', 'Kronk', 'tKronker@gmail.com', 'What is the name of your first pet?', 'Bowser', 'admin' ),
		  ('employee', '$2a$10$U3LA8nuobCKh9mN67/MXmu8D9yT0d3rFZsi/j77R93lq27jBzzHou', 'Gillian', 'Bunter', 'bunterG@gmail.com', 'What is the name of your first pet?', 'Banjo', 'employee' ),
		  ('user', '$2a$10$d6JTmNpwizj2usF7ro751OMP3sWfey4THaORUgrcgK9zKxzFYVt7q', 'Nick', 'Young', 'NickYoung89@hotmail.com', 'What is the name of your primary school?', 'Apleton', 'user' ),
		  ('kylePanzonee99', '$2a$10$yWQXNA/qbqpVoYo02htr0e95rngl64Ar59AchVvIDNhfrsq6DNBVC', 'Kyle', 'Cork', 'Korker@gmail.com', 'What is the name of your first pet?', 'Bruno', 'user' ),
		  ('catFan', '$2a$10$4x.DYsIRzx8mq.EJH1lhdeeIRo2X8fkuoPd7OHjQf6JDreKP/6iHS', 'Karen', 'Groose', 'karencats@gmail.com', 'What is your favorite movie?', 'Cats', 'user' ),
		  ('rockerdude96', '$2a$10$MwidjcNqFIGzmRY2rxEREe33HLP1YXacUCmC1u9vhcdEzReeRJHdO', 'Evan', 'Werk', 'werkit@gmail.com', 'What is the name of your first pet?', 'Milo', 'user' ),
		  ('brunogood', '$2a$10$JMCPVgUjVYCKvdBRZKVo6OjvWiYKU.XFNILfhLYU9lJm/Axlvn79W', 'Bruno', 'Laklochloheskevsky', 'buno@gmail.com', 'What is the name of your first pet?', 'Dog', 'user' ),
		  ('not_emily_here', '$2a$10$jAoDdnid4bpUpD62JsAVIOeJ9lmgvJdCB0uVl42NRp8J.nwyhWLOK', 'Emily', 'Dolittle', 'talks2animals@gmail.com', 'What is your favorite movie?', 'Beetlejuice', 'user' ),
		  ('uhda_testuser', '$2a$10$d6JTmNpwizj2usF7ro751OMP3sWfey4THaORUgrcgK9zKxzFYVt7q', 'UHDA', 'TestUser', 'uhda.untitled.testuser@gmail.com', 'What is the name of your first pet?', 'Rufus', 'user');

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
		   ('not_emily_here', 'ROLE_USER'),
		   ('uhda_testuser', 'ROLE_USER');

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
		VALUES ('Treeflex', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 'High', 'In Progress', 'catFan', 'employee', 'Internet', '2019-12-07 06:42:21', null, '2020-03-03 09:09:38'),
			   ('Span', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus.', 'Low', 'Closed', 'catFan', 'employee', 'Software', '2020-03-11 19:43:45', '2019-11-10 17:54:37', '2019-11-10 17:54:37'),
			   ('Cardify', 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 'Low', 'In Progress', 'user', 'employee', 'Installation Request', '2020-02-10 05:18:21', null, '2020-09-24 04:21:54'),
			   ('Stronghold', 'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.', 'Trivial', 'Closed', 'kylePanzonee99', 'employee', 'Hardware', '2020-03-11 03:36:42', '2020-08-11 11:23:22', '2020-08-11 11:23:22'),
			   ('Alpha', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 'Trivial', 'Resolved', 'not_emily_here', 'employee', 'Internet', '2020-04-28 12:08:24', null, '2020-08-21 10:30:17'),
			   ('Home Ing', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 'High', 'Resolved', 'admin', 'employee', 'Installation Request', '2020-03-04 10:29:23', null, '2020-08-12 04:41:07'),
			   ('Cardify', 'Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', 'Trivial', 'Resolved', 'catFan', 'employee', 'Installation Request', '2020-06-12 04:31:24', null, '2019-12-05 03:24:47'),
			   ('Tampflex', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 'Low', 'In Progress', 'employee', 'employee', 'Internet', '2020-05-16 17:18:22', null, '2020-08-01 15:25:14'),
			   ('Asoka', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 'Normal', 'Open', 'rockerdude96', 'employee', 'Hardware', '2020-05-06 08:33:13', null, '2020-06-26 11:51:22'),
			   ('Fixflex', 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui.', 'High', 'Open', 'employee', 'employee', 'Software', '2020-10-08 02:27:24', null, '2020-02-27 19:21:28'),
			   ('Trippledex', 'Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 'Normal', 'In Progress', 'admin', 'employee', 'Installation Request', '2020-10-04 20:49:12', null, '2020-02-20 14:12:09'),
			   ('Zoolab', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo.', 'Critical', 'Closed', 'brunogood', 'employee', 'Hardware', '2020-09-26 11:05:19', '2020-09-09 13:17:21', '2020-09-09 13:17:21'),
			   ('Pannier', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 'Low', 'In Progress', 'brunogood', 'employee', 'Internet', '2019-11-25 02:09:20', null, '2020-06-27 10:28:36'),
			   ('Stronghold', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.', 'Normal', 'In Progress', 'employee', 'employee', 'Software', '2020-04-08 09:57:08', null, '2020-11-01 03:45:40'),
			   ('Domainer', 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 'Normal', 'Open', 'catFan', 'employee', 'Software', '2020-04-25 21:31:48', null, '2020-08-19 13:52:06'),
			   ('Tampflex', 'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 'Critical', 'In Progress', 'brunogood', 'employee', 'General', '2020-05-12 07:35:00', null, '2020-05-18 13:20:54'),
			   ('Kanlam', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 'Normal', 'Open', 'brunogood', 'employee', 'Hardware', '2020-08-10 17:16:21', null, '2020-04-20 10:53:31'),
			   ('Cardify', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 'Normal', 'In Progress', 'catFan', 'employee', 'Installation Request', '2020-03-02 08:46:24', null, '2019-11-09 12:56:51'),
			   ('Domainer', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.', 'High', 'In Progress', 'not_emily_here', 'employee', 'Installation Request', '2020-05-09 05:11:54', null, '2020-11-04 07:07:47'),
			   ('Viva', 'Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', 'Trivial', 'Closed', 'employee', 'employee', 'Internet', '2020-06-26 00:55:06', '2020-07-09 06:02:56', '2020-07-09 06:02:56'),
			   ('Temp', 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Mauris viverra diam vitae quam. Suspendisse potenti.', 'Normal', 'Open', 'catFan', 'employee', 'Internet', '2020-01-28 16:38:05', null, '2020-01-31 03:59:34'),
			   ('Wrapsafe', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae, Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'High', 'Closed', 'brunogood', 'employee', 'Internet', '2020-10-21 04:17:25', '2020-10-18 07:35:49', '2020-10-18 07:35:49'),
			   ('FINTOne', 'Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 'Low', 'Resolved', 'catFan', 'employee', 'Hardware', '2020-04-19 22:12:52', null, '2020-03-17 16:18:07'),
			   ('Gembucket', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.', 'Trivial', 'Open', 'catFan', 'employee', 'Software', '2019-12-16 02:45:22', null, '2020-02-28 13:34:31'),
			   ('Domainer', 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 'Normal', 'Closed', 'catFan', 'employee', 'Software', '2020-02-27 14:40:39', '2019-11-28 20:33:36', '2019-11-28 20:33:36');

CREATE TABLE comments(
    commentId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    ticketId INT NOT NULL ,
    author VARCHAR(30),
    value VARCHAR(800),
    dateCreated TIMESTAMP NOT NULL ,
    timeFinished TIMESTAMP NOT NULL ,
    FOREIGN KEY(ticketId) REFERENCES tickets(ticketId)
);

CREATE TABLE slack_association (
	email VARCHAR(50) NOT NULL,
	slackUserId VARCHAR(50),
	FOREIGN KEY (email) REFERENCES users (email)
);

INSERT INTO slack_association (email, slackUserId)
		VALUES ('uhda.untitled.testuser@gmail.com', 'U01GBLEGAM6');
		

CREATE TABLE hardwareType(
    hardwareTypeId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
	hardwareTypeDescription VARCHAR(100) NOT NULL ,
);

INSERT INTO hardwareType (hardwareTypeDescription) 
		VALUES ('Dell Inspiron Desktop'),
			   ('Dell 560 Laptop'),
			   ('Lenovo Yoga 1090 Laptop'),
			   ('Brother Printer'),
			   ('IPhone 12'),
			   ('IPhone 10');

CREATE TABLE hardwareAssignment(
    hardwareId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
	hardwareTypeName VARCHAR(100) NOT NULL ,
	status VARCHAR(30) NOT NULL ,
	usernameAssignedTo VARCHAR(30) NOT NULL ,
    dateAssigned TIMESTAMP NOT NULL ,
    dateReturned TIMESTAMP,
    FOREIGN KEY(usernameAssignedTo) REFERENCES users(username),
	FOREIGN KEY(hardwareTypeName) REFERENCES hardwareType(hardwareTypeDescription)
);

INSERT INTO hardwareAssignment (hardwareTypeName, status, usernameAssignedTo, dateAssigned, dateReturned) 
		VALUES ('Dell Inspiron Desktop', 'Assigned', 'user', '2019-12-07 06:42:21', null),
			   ('Dell 560 Laptop', 'Assigned', 'catFan', '2020-11-07 06:42:21', null),
			   ('Dell 560 Laptop', 'Assigned', 'employee', '2020-11-07 06:42:21', null),
			   ('Dell 560 Laptop', 'Assigned', 'admin', '2020-11-07 06:42:21', null),
			   ('Lenovo Yoga 1090 Laptop', 'Returned', 'user', '2020-07-07 06:42:21', '2019-12-07 06:42:21'),
			   ('Brother Printer', 'Assigned', 'employee', '2020-07-07 06:42:21', null),
			   ('IPhone 12', 'Lost', 'employee', '2020-07-07 06:42:21', null),
			   ('IPhone 12', 'Assigned', 'admin', '2020-07-07 06:42:21', null);





			   
