
-- Hardware		   
DROP TABLE IF EXISTS hardware;
DROP TABLE IF EXISTS hardwareAssignment;

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
			   

-- Hardware	Type   
DROP TABLE IF EXISTS hardwareType;

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


			   
