INSERT INTO milestone(id, content, deadline, title)
VALUES (1, '마일스톤1내용', '2022-07-01', '마일스톤1'),
       (2, '마일스톤2내용', '2022-07-02', '마일스톤2'),
       (3, '마일스톤3내용', '2022-07-03', '마일스톤3'),
       (4, '마일스톤4내용', '2022-07-09', '마일스톤4'),
       (5, '마일스톤5내용', '2022-07-10', '마일스톤5');

INSERT INTO label(id, title, content, background_color, text_color)
VALUES (1, '레이블1', '레이블1설명', '#FFFF00', '#000000'),
       (2, '레이블2', '2설명', '#FFA500', '#000000'),
       (3, '레이블3', '3설명', '#AFEEEE', '#000000');

INSERT INTO members(id, github_id, image_url, name)
    VALUE (1, 'leekm310', 'https://avatars.githubusercontent.com/u/87681380?v=4', 'K Lee');

INSERT INTO issue(id, content, title, created_time, issues_status, member_id, milestone_id)
VALUES (1, '테스트1', '테스트입니다1', '2022-07-06 12:00:00', false , 1, 1),
       (2, '테스트2', '테스트입니다22', '2022-07-07 12:00:00', true , 1, 2),
       (3, '테스트3', '테스트입니다333', '2022-07-08 12:00:00', true , 1, 3);

INSERT INTO issue_labels(id, issue_id, label_id)
VALUES (1, 2, 1),
       (2, 2, 2),
       (3, 3, 3);

