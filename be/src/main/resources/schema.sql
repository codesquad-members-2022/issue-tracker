DROP TABLE IF EXISTS issue_label;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS assignee;
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS milestone;
DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    member_id   BIGINT NOT NULL AUTO_INCREMENT,
    type        VARCHAR(32),
    identity    VARCHAR(255),
    password    VARCHAR(255),
    email       VARCHAR(255),
    name    VARCHAR(32),
    profile_url VARCHAR(255),
    PRIMARY KEY (member_id)
);

CREATE TABLE milestone
(
    milestone_id BIGINT NOT NULL AUTO_INCREMENT,
    subject      VARCHAR(64),
    description  VARCHAR(255),
    end_date     TIMESTAMP,
    PRIMARY KEY (milestone_id)
);

CREATE TABLE issue
(
    issue_id         BIGINT NOT NULL AUTO_INCREMENT,
    member_id        BIGINT NOT NULL,
    milestone_id     BIGINT,
    subject          VARCHAR(255),
    description      VARCHAR(255),
    status           VARCHAR(32),
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    PRIMARY KEY (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (milestone_id) REFERENCES milestone (milestone_id)
);

CREATE TABLE image (
    image_id BIGINT NOT NULL AUTO_INCREMENT,
    issue_id BIGINT NOT NULL,
    url VARCHAR (255),
    PRIMARY KEY (image_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id)
);

CREATE TABLE assignee
(
    assignee_id       BIGINT NOT NULL AUTO_INCREMENT,
    issue_id          BIGINT NOT NULL,
    member_id         BIGINT NOT NULL,
    PRIMARY KEY (assignee_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE reply
(
    reply_id         BIGINT NOT NULL AUTO_INCREMENT,
    issue_id         BIGINT NOT NULL,
    member_id        BIGINT NOT NULL,
    content          VARCHAR(255),
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE label
(
    label_id    BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(64),
    description VARCHAR(255),
    color       VARCHAR(64),
    PRIMARY KEY (label_id)
);

CREATE TABLE issue_label
(
    issue_label_id BIGINT NOT NULL AUTO_INCREMENT,
    issue_id       BIGINT NOT NULL,
    label_id       BIGINT NOT NULL,
    PRIMARY KEY (issue_label_id),
    FOREIGN KEY (issue_id) REFERENCES issue (issue_id),
    FOREIGN KEY (label_id) REFERENCES label (label_id)
);
