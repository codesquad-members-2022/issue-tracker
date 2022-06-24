insert into label(background_color, text_color, description, title)
values ('#000000', '#FFFFFF', 'bug fix', 'bug'),
       ('#00008b', '#FFFFFF', 'new feature', 'feature'),
       ('#a9a9a9', '#FFFFFF', 'new documentation', 'documentation');

insert into milestone(created_date_time, modified_date_time, description, dead_line, status, title)
values (now(), now(), 'milestone1 마일스톤에 대한 설명', '2021-06-21', 'CLOSED', 'milestone1'),
       (now(), now(), 'milestone2 마일스톤에 대한 설명', '2021-07-01', 'OPEN', 'milestone2'),
       (now(), now(), 'milestone3 마일스톤에 대한 설명', '2021-07-01', 'OPEN', 'milestone3');

insert into members(`name`, email, image_url, token)
values ('KTH-96','taehyun960@gamil.com','https://avatars.githubusercontent.com/u/75709176?v=4', 'e98f3701c96af24cdeaa'),
       ('aaaa','aaaaa@gamil.com','https://avatars.githubusercontent.com/u/75709176?v=4', 'e98f3701c96af24cdeaa'),
       ('bbbb','bbbbb0@gamil.com','https://avatars.githubusercontent.com/u/75709176?v=4', 'e98f3701c96af24cdeaa');

insert into issue(created_date_time, modified_date_time, content, status, title, milestone_id, member_id)
values (now(), now(), 'issue1번 설명입니다.', 'CLOSED', 'issue1', 1, 1),
       (now(), now(), 'issue2번 설명입니다.', 'OPEN', 'issue2', 2, 2),
       (now(), now(), 'issue3번 설명입니다.', 'CLOSED', 'issue3', 3, 3);

insert into comments(created_date_time, modified_date_time, content, issue_id, member_id)
values (now(), now(), 'issue1번 첫번째 댓글입니다.', 1, 2),
       (now(), now(), 'issue1번 두번째 댓글입니다.', 1, 3),
       (now(), now(), 'issue2번 첫번째 댓글입니다.', 2, 1),
       (now(), now(), 'issue2번 두번째 댓글입니다.', 2, 3),
       (now(), now(), 'issue3번 첫번째 댓글입니다.', 3, 1),
       (now(), now(), 'issue3번 두번째 댓글입니다.', 3, 2);

insert into assignee(issue_id, member_id)
values (1, 1),
       (1, 3),
       (2, 1),
       (2, 3),
       (3, 2),
       (3, 3);

insert into issue_label(issue_id, label_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 2),
       (3, 3);
