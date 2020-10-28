DROP TABLE users IF EXISTS;

CREATE TABLE users(
username VARCHAR(30) PRIMARY KEY,
password VARCHAR(30),
first_name VARCHAR(30),
last_name VARCHAR(30),
email VARCHAR(50),
security_question VARCHAR(60),
security_answer VARCHAR(20),
reset_token VARCHAR(50),
role VARCHAR(20)
);


INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('admin', 'a', 'Tyler', 'Kronk', 'tKronker@gmail.com', 'What is the name of your first pet?', 'Bowser', 'admin' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('employee', 'e', 'Gillian', 'Bunter', 'bunterG@gmail.com', 'What is the name of your first pet?', 'Banjo', 'employee' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('user', 'u', 'Nick', 'Young', 'NickYoung89@hotmail.com', 'What is the name of your primary school?', 'Apleton', 'user' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('kylePanzonee99', 'password', 'Kyle', 'Cork', 'Korker@gmail.com', 'What is the name of your first pet?', 'Bruno', 'user' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('catFan', 'iluvcats', 'Karen', 'Groose', 'karencats@gmail.com', 'What is your favorite movie?', 'Cats', 'user' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('rockerdude96', 'ripVan', 'Evan', 'Werk', 'werkit@gmail.com', 'What is the name of your first pet?', 'Milo', 'user' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('brunogood', 'isnice', 'Bruno', 'Laklochloheskevsky', 'buno@gmail.com', 'What is the name of your first pet?', 'Dog', 'user' );
INSERT INTO users(username, password, first_name, last_name, email, security_question, security_answer, role) VALUES('not_emily_here', 'nopers', 'Emily', 'Dolittle', 'talks2animals@gmail.com', 'What is your favorite movie?', 'Beetlejuice', 'user' );
