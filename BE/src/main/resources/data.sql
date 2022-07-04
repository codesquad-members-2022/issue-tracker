INSERT INTO milestone(id, content, deadline, title)
VALUES (1, '뫄뫄기능개발', '2022-06-26', '마일스톤1'),
       (2, '솨솨기능개발', '2022-06-27', '마일스톤2'),
       (3, '롸롸기능개발', '2022-06-28', '마일스톤3'),
       (4, '배포', '2022-06-29', '마일스톤4'),
       (5, '리팩토링', '2022-06-30', '마일스톤5');

INSERT INTO label(id, title, content, background_color, text_color)
VALUES (1, 'AOS', '안드로이드', '#FFFF00', '#000000'),
       (2, 'BE', '백엔드', '#FFA500', '#000000'),
       (3, 'Feature', '기능구현', '#AFEEEE', '#000000'),
       (4, 'Docs', '문서작업', '#AFEEEE', '#000000');

INSERT INTO members(id, github_id, image_url, name)
VALUES (1, 'leekm310', 'https://avatars.githubusercontent.com/u/87681380?v=4', 'K Lee'),
       (2, 'tester1', 'https://www.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png', 'tester');

INSERT INTO issue(id, content, title, created_time, issues_status, member_id, milestone_id)
VALUES (1, '뫄뫄기능개발', '테스트입니다1', '2022-06-26 12:00:00', false , 1, 1),
       (2, '솨솨기능개발', '테스트입니다22', '2022-06-27 12:00:00', true , 1, 2),
       (3, '리팩토링응급', '테스트입니다333', '2022-06-30 12:00:00', true , 1, 3),
       (4, '살려주세요', '테스트입니다333', '2022-06-30 12:00:00', true , 1, 3),
       (5, '이슈쓰', '테스트입니다333', '2022-06-30 12:00:00', true , 1, 3);

INSERT INTO issue_labels(id, issue_id, label_id)
VALUES (1, 2, 1),
       (2, 2, 2),
       (3, 3, 3);

INSERT INTO  issue_members(id, issue_id, member_id)
VALUES (1, 2, 1),
       (2, 3, 2);

INSERT INTO comments(id, content, created_time, issue_id, member_id)
VALUES (1, '코멘트테스트', '2022-06-27 13:42:00', 2, 1),
       (2, '안녕하세요', '2022-06-29 13:42:00', 2, 2),
       (3, '백엔드짱', '2022-06-29 14:22:00', 3, 1),
       (4, '안드로이드짱', '2022-06-29 15:40:00', 4, 2);


