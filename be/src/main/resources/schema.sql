DROP TABLE IF EXISTS issue_milestone;
DROP TABLE IF EXISTS issue_label;
DROP TABLE IF EXISTS milestone;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS issue_assignee;
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    member_id   INT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(255),
    password    VARCHAR(255),
    name        VARCHAR(32),
    profile_url VARCHAR(255),
    PRIMARY KEY (member_id)
);

CREATE TABLE issue
(
    issue_id         INT NOT NULL AUTO_INCREMENT,
    member_id        INT NOT NULL,
    issue_number     INT,
    subject          VARCHAR(255),
    description      VARCHAR(255),
    writer           VARCHAR(255),
    image_url        VARCHAR(255),
    status           VARCHAR(32),
    created_datetime TIMESTAMP,
    PRIMARY KEY (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE issue_assignee
(
    issue_assignee_id INT NOT NULL AUTO_INCREMENT,
    issue_id          INT NOT NULL,
    member_id         INT NOT NULL,
    PRIMARY KEY (issue_assignee_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE reply
(
    reply_id         INT NOT NULL AUTO_INCREMENT,
    issue_id         INT NOT NULL,
    member_id        INT NOT NULL,
    content          VARCHAR(255),
    created_datetime TIMESTAMP,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE label
(
    label_id    INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(64),
    description VARCHAR(255),
    PRIMARY KEY (label_id)
);

CREATE TABLE milestone
(
    milestone_id INT NOT NULL AUTO_INCREMENT,
    subject      VARCHAR(64),
    description  VARCHAR(255),
    end_time     TIMESTAMP,
    PRIMARY KEY (milestone_id)
);

CREATE TABLE issue_label
(
    issue_label_id INT NOT NULL AUTO_INCREMENT,
    issue_id       INT NOT NULL,
    label_id       INT NOT NULL,
    PRIMARY KEY (issue_label_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (label_id) REFERENCES label (label_id)
);

CREATE TABLE issue_milestone
(
    issue_milestone_id INT NOT NULL AUTO_INCREMENT,
    issue_id           INT NOT NULL,
    milestone_id       INT NOT NULL,
    PRIMARY KEY (issue_milestone_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (milestone_id) REFERENCES milestone (milestone_id)
);
