alter table assignee
    drop
        foreign key fk_assignee_issue;

alter table assignee
    drop
        foreign key fk_assignee_member;

alter table comment
    drop
        foreign key fk_comment_issue;

alter table comment
    drop
        foreign key fk_comment_member;

alter table emoji
    drop
        foreign key fk_emoji_comment;

alter table emoji
    drop
        foreign key fk_emoji_issue;

alter table image
    drop
        foreign key fk_image_comment;

alter table image
    drop
        foreign key fk_image_issue;

alter table issue
    drop
        foreign key fk_issue_author;

alter table issue
    drop
        foreign key fk_issue_issue_group;

alter table issue
    drop
        foreign key fk_issue_milestone;

alter table issue_group_member
    drop
        foreign key fk_issue_group_group;

alter table issue_group_member
    drop
        foreign key fk_issue_group_member_member;

alter table issue_label
    drop
        foreign key fk_issue_label_issue;

alter table issue_label
    drop
        foreign key fk_issue_label_label;

drop table if exists assignee;

drop table if exists comment;

drop table if exists emoji;

drop table if exists emotion;

drop table if exists image;

drop table if exists issue;

drop table if exists issue_group;

drop table if exists issue_group_member;

drop table if exists issue_label;

drop table if exists label;

drop table if exists member;

drop table if exists milestone;

create table assignee
(
    id        bigint not null auto_increment,
    issue_id  bigint not null,
    member_id bigint not null,
    primary key (id)
) engine = InnoDB;

create table comment
(
    id               bigint not null auto_increment,
    created_at       datetime(6),
    last_modified_at datetime(6),
    content          longtext,
    deleted          bit    not null,
    issue_id         bigint not null,
    member_id        bigint not null,
    primary key (id)
) engine = InnoDB;

create table emoji
(
    id         bigint not null auto_increment,
    emotion    varchar(4),
    comment_id bigint,
    issue_id   bigint not null,
    primary key (id)
) engine = InnoDB;

create table emotion
(
    id      bigint not null auto_increment,
    emotion varchar(255),
    primary key (id)
) engine = InnoDB;

create table image
(
    id         bigint not null auto_increment,
    file_name  varchar(4096),
    url        varchar(2048),
    comment_id bigint,
    issue_id   bigint not null,
    primary key (id)
) engine = InnoDB;

create table issue
(
    id               bigint       not null auto_increment,
    created_at       datetime(6),
    last_modified_at datetime(6),
    deleted          bit          not null,
    issue_status     varchar(255) not null,
    title            varchar(60),
    author_id        bigint       not null,
    issue_group_id   bigint       not null,
    milestone_id     bigint,
    content          longtext     null,
    primary key (id)
) engine = InnoDB;

create table issue_group
(
    id               bigint not null auto_increment,
    created_at       datetime(6),
    last_modified_at datetime(6),
    name             varchar(100),
    primary key (id)
) engine = InnoDB;

create table issue_group_member
(
    id             bigint not null auto_increment,
    issue_group_id bigint not null,
    member_id      bigint not null,
    primary key (id)
) engine = InnoDB;

create table issue_label
(
    id       bigint not null auto_increment,
    issue_id bigint not null,
    label_id bigint not null,
    primary key (id)
) engine = InnoDB;

create table label
(
    id          bigint not null auto_increment,
    color       char(6),
    description varchar(100),
    name        varchar(50),
    primary key (id)
) engine = InnoDB;

create table member
(
    id                bigint not null auto_increment,
    deleted           bit    not null,
    github_id         varchar(255),
    name              varchar(39),
    profile_image_url varchar(2048),
    primary key (id)
) engine = InnoDB;

create table milestone
(
    id               bigint not null auto_increment,
    created_at       datetime(6),
    last_modified_at datetime(6),
    deadline         date,
    description      varchar(8192),
    title            varchar(255),
    issue_group_id   bigint,
    primary key (id)
) engine = InnoDB;

alter table assignee
    add constraint fk_assignee_issue
        foreign key (issue_id)
            references issue (id);

alter table assignee
    add constraint fk_assignee_member
        foreign key (member_id)
            references member (id);

alter table comment
    add constraint fk_comment_issue
        foreign key (issue_id)
            references issue (id);

alter table comment
    add constraint fk_comment_member
        foreign key (member_id)
            references member (id);

alter table emoji
    add constraint fk_emoji_comment
        foreign key (comment_id)
            references comment (id);

alter table emoji
    add constraint fk_emoji_issue
        foreign key (issue_id)
            references issue (id);

alter table image
    add constraint fk_image_comment
        foreign key (comment_id)
            references comment (id);

alter table image
    add constraint fk_image_issue
        foreign key (issue_id)
            references issue (id);

alter table issue
    add constraint fk_issue_author
        foreign key (author_id)
            references member (id);

alter table issue
    add constraint fk_issue_issue_group
        foreign key (issue_group_id)
            references issue_group (id);

alter table issue
    add constraint fk_issue_milestone
        foreign key (milestone_id)
            references milestone (id);

alter table issue_group_member
    add constraint fk_issue_group_group
        foreign key (issue_group_id)
            references issue_group (id);

alter table issue_group_member
    add constraint fk_issue_group_member_member
        foreign key (member_id)
            references member (id);

alter table issue_label
    add constraint fk_issue_label_issue
        foreign key (issue_id)
            references issue (id);

alter table issue_label
    add constraint fk_issue_label_label
        foreign key (label_id)
            references label (id);

alter table milestone
    add constraint fk_milestone_issue_group
        foreign key (issue_group_id)
            references issue_group (id);
