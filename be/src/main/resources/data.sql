INSERT INTO member (type, identity, password, email, name, profile_url) VALUES ('NORMAL', 'ikjo', '1234', 'auddlr100@naver.com', '익조', 'https://avatars.githubusercontent.com/u/82401504?v=4');
INSERT INTO member (type, identity, password, email, name, profile_url) VALUES ('NORMAL', 'park', '1234', 'park@naver.com', '파크', 'https://avatars.githubusercontent.com/u/58503584?v=4');
INSERT INTO member (type, identity, password, email, name, profile_url) VALUES ('NORMAL', 'alan', '1234', 'alan@naver.com', '앨런', 'https://avatars.githubusercontent.com/u/95538993?v=4');

INSERT INTO milestone (subject, description, end_date)
VALUES ('이슈 트래커 서비스 구현', '프론트 및 백엔드 구현', NOW() + INTERVAL 14 DAY),
       ('숙소 예약 서비스 구현', '프론트 및 백엔드 구현', NOW() + INTERVAL 14 DAY),
       ('반찬 주문 서비스 구현', '프론트 및 백엔드 구현', NOW() + INTERVAL 14 DAY);

INSERT INTO issue (member_id, milestone_id, subject, description, status, created_datetime, updated_datetime)
VALUES (1, 1, '백엔드 기능 구현', '백엔드', 'OPEN', NOW(), NOW()),
       (2, 1, '프론트엔드 기능 구현', '프론트엔드', 'OPEN', NOW(), NOW()),
       (3, null, '배포', '배포', 'OPEN', NOW(), NOW()),
       (1, 2, '백엔드 기능 구현', '백엔드', 'CLOSED', NOW(), NOW());

INSERT INTO label (name, description, color, dark_text_flag)
VALUES ('be', '백엔드 라벨', '#333333', 0),
       ('fe', '프론트엔드 라벨', '#222222', 0),
       ('bug', '버그 해결', '#444444', 0);

INSERT INTO issue_label (issue_id, label_id) VALUES (1, 1), (2, 1), (1, 3), (2, 2);

INSERT INTO assignee (issue_id, member_id) VALUES(1, 1);

INSERT INTO reply (issue_id, member_id, content, created_datetime, updated_datetime)
VALUES (1, 1, '굿!!', NOW(), NOW()), (2, 2, '굿!!', NOW(), NOW()), (3, 3, '굿!!', NOW(), NOW());
