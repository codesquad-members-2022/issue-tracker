INSERT INTO member(created_at, updated_at, member_id, avatar_url) VALUES (now(), now(), 'ron2', 'asdfasdf.com');
INSERT INTO member(created_at, updated_at, member_id, avatar_url) VALUES (now(), now(), 'ader', 'ader.com');

INSERT INTO milestone(title, description, end_date) VALUES ('week1 milestone', 'week1 description', now());

INSERT INTO label(title, color, description) VALUES ('be label', 'red', 'is backend label');
INSERT INTO label(title, color, description) VALUES ('fe label', 'blue', 'is frontend label');


INSERT INTO issue (issuer_id, title, contents, open_status, milestone_id) VALUES (1, "first issue", "first issue contents", true, 1);
INSERT INTO issue (issuer_id, title, contents, open_status, milestone_id) VALUES (1, "second issue", "second issue contents", true, 1);
INSERT INTO issue (issuer_id, title, contents, open_status, milestone_id) VALUES (1, "third issue", "third issue contents", true, 1);

INSERT INTO issue_label (issue_id, label_id) VALUES (1, 1);
INSERT INTO issue_label (issue_id, label_id) VALUES (1, 2);
INSERT INTO issue_label (issue_id, label_id) VALUES (2, 1);
INSERT INTO issue_label (issue_id, label_id) VALUES (3, 1);

INSERT INTO issue_assignee (issue_id, assignee_id) VALUES (1, 1);
INSERT INTO issue_assignee (issue_id, assignee_id) VALUES (1, 2);
INSERT INTO issue_assignee (issue_id, assignee_id) VALUES (2, 1);
INSERT INTO issue_assignee (issue_id, assignee_id) VALUES (3, 1);
