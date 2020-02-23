drop database if exists TextbookReview;
create database TextbookReview;
use TextbookReview;

create table genre(
    genre varchar(10) not null,
    primary key(genre)
);

create table user(
    id varchar(32),
    name varchar(20) not null,
    password varchar(30) not null,
    email varchar(40) not null,
    primary key(id)
);

create table textbook(
    isbn varchar(13),
    name varchar(20) not null,
    genre varchar(10) not null,
    star int unsigned not null,
    primary key(isbn),
    foreign key(genre) references genre(genre)
);

create table review(
    rid varchar(32),
    uid varchar(32) not null,
    isbn varchar(13) not null,
    date date not null,
    review varchar(200) not null,
    star int unsigned not null,
    primary key(rid),
    foreign key(uid) references user(id),
    foreign key(isbn) references textbook(isbn)
);

insert into genre(genre) values('计算机科学');
insert into genre(genre) values('数学');
insert into genre(genre) values('英语');
insert into genre(genre) values('语文');
