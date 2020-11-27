DROP TABLE IF EXISTS slack_association;

CREATE TABLE slack_association (
	email VARCHAR(50) NOT NULL,
	slackUserId VARCHAR(50),
	FOREIGN KEY (email) REFERENCES users (email)
);

INSERT INTO slack_association (email, slackUserId)
		VALUES ('uhda.untitled.testuser@gmail.com', 'U01GBLEGAM6');