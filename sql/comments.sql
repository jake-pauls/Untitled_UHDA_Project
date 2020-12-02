DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
    commentId INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    ticketId INT NOT NULL ,
    author VARCHAR(30),
    value VARCHAR(800),
    dateCreated TIMESTAMP NOT NULL ,
    
    FOREIGN KEY(ticketId) REFERENCES tickets(ticketId)
);