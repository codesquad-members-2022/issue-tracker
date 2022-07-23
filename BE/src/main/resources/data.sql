INSERT INTO member
    (id, social_id, avatar_image_url, refresh_token)
VALUES
    (1, 'Louie-03', 'https://avatars.githubusercontent.com/u/92966772?v=4', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoVG9rZW4iLCJhdWQiOiIxIiwiaXNzIjoibG91aWUxc2UiLCJleHAiOjE2NTY1NjQ4MjYsImlhdCI6MTY1NTk2MDAyNiwibWVtYmVySWQiOjF9.kyKemvZjW3d3PVJ64QI3SJg6ofZWCbu49I1Jg4n9fwk'),
    (2, 'User-A', 'https://avatars.githubusercontent.com/u/92966772?v=4', null),
    (3, 'User-B', 'https://avatars.githubusercontent.com/u/92966772?v=4', null),
    (4, 'User-C', 'https://avatars.githubusercontent.com/u/92966772?v=4', null),
    (5, 'User-D', 'https://avatars.githubusercontent.com/u/92966772?v=4', null),
    (6, 'User-E', 'https://avatars.githubusercontent.com/u/92966772?v=4', null);

INSERT INTO milestone
    (id, completed_date, milestone_description, title)
VALUES
    (1, '2022-6-5-16-00', '마일스톤 설명 1', '마일스톤 제목 1'),
    (2, '2022-6-20-16-00', '마일스톤 설명 2', '마일스톤 제목 2'),
    (3, '2022-7-5-16-00', '마일스톤 설명 3', '마일스톤 제목 3'),
    (4, '2022-7-15-16-00', '마일스톤 설명 4', '마일스톤 제목 4'),
    (5, '2022-7-25-16-00', '마일스톤 설명 5', '마일스톤 제목 5');

INSERT INTO label
    (id, background_color, label_description, label_name, text_color)
VALUES
    (1, '#004DE3', '라벨 설명 1', '라벨 이름 1', 'BRIGHT'),
    (2, '#FFFF00', '라벨 설명 2', '라벨 이름 2', 'DARK'),
    (3, '#FFFF66', '라벨 설명 3', '라벨 이름 3', 'BRIGHT'),
    (4, '#00FFFF', '라벨 설명 4', '라벨 이름 4', 'DARK'),
    (5, '#9999FF', '라벨 설명 5', '라벨 이름 5', 'BRIGHT');

INSERT INTO issue
    (id, create_date_time, status, title, milestone_id, writer_id)
VALUES
    (1, '2022-6-5-16-00', 'OPEN', '이슈 제목 1', 1, 1),
    (2, '2022-7-5-16-00', 'OPEN', '이슈 제목 2', 2, 2),
    (3, '2022-7-15-16-00', 'CLOSE', '이슈 제목 3', 3, 3),
    (4, '2022-8-15-16-00', 'CLOSE', '이슈 제목 4', 4, 4),
    (5, '2022-8-25-16-00', 'CLOSE', '이슈 제목 5', 5, 5);

INSERT INTO issue_label
    (id, issue_id, label_id)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 2, 1),
    (4, 3, 3),
    (5, 4, 5);
