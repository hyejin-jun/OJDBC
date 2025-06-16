

create table member (
mno number (5) not null,
writer nvarchar2(10) not null,
id nvarchar2 (10) primary key,
pw nvarchar2 (10) not null,
mdate date default sysdate not null
)   --  테이블 생성

select * from member_objects where object type


select * from member


drop table member

delete from member

create sequence member_seq increment by 1 start with 1 nocycle nocache  -- 시퀀스

insert into member (mno, writer, id, pw)  -- 더미데이터 입력
values (member_seq.nextval, '제베원', 'zb1', '1234')
insert into member (mno, writer, id, pw)  -- 더미데이터 입력
values (member_seq.nextval, '투어스', 'tws', '1234')
insert into member (mno, writer, id, pw)  -- 더미데이터 입력
values (member_seq.nextval, '라이즈', 'riize', '1234')
insert into member (mno, writer, id, pw)  -- 더미데이터 입력
values (member_seq.nextval, '보넥도', 'bnd', '1234')
insert into member (mno, writer, id, pw)  -- 더미데이터 입력
values (member_seq.nextval, '위시', 'wish', '1234')

------------------------------------------------------------------------------------------

create table letter (
bno number (5) not null,
writer nvarchar2(10) not null,
title nvarchar2 (10) primary key,
detail nvarchar2 (1000) not null,
bdate date default sysdate not null
)   --  테이블 생성

select * from letter

drop table letter

create sequence letter_seq increment by 1 start with 1 nocycle nocache

insert into letter (bno, writer, title,detail)  -- 더미데이터 입력
values (letter_seq.nextval, '제베원', '제베원♡', 'Only One Story')
insert into letter (bno, writer, title,detail)  -- 더미데이터 입력
values (letter_seq.nextval, '투어스', '투어스♡', '첫 만남은 계획처럼 되지 않아')
insert into letter (bno, writer, title,detail)  -- 더미데이터 입력
values (letter_seq.nextval, '라이즈', '라이즈♡', '겟 어 기타')
insert into letter (bno, writer, title,detail)  -- 더미데이터 입력
values (letter_seq.nextval, '보넥도', '보넥도♡', '부모님 관람 불가')
insert into letter (bno, writer, title,detail)  -- 더미데이터 입력
values (letter_seq.nextval, '위시', '위시♡', '스테이')