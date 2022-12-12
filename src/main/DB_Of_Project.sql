-----------------------------------------   Create new user ------------------------------------

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER chat_app_db IDENTIFIED BY "chatapp";
GRANT CONNECT TO chat_app_db;
GRANT CREATE SESSION,GRANT ANY PRIVILEGE TO chat_app_db;
GRANT UNLIMITED TABLESPACE TO chat_app_db;
GRANT CREATE TABLE TO chat_app_db;

------------------------------------------   Create tables -------------------------------------
create table msg_table(
    id number,
    sender_id varchar2(20),
    reciver_id varchar2(20),
    message varchar2(4000),
    date_send date default sysdate
);
--alter table msg_table add date_send date default sysdate;
alter table msg_table add constraint msg_pk primary key(id);
alter table msg_table add constraint msg_fk FOREIGN key(reciver_id) references accounts(username);
desc msg_table;
--drop table msg_table;

insert into msg_table (id,sender_id,reciver_id,message) values (1,'yassine','test1','fin asat');
insert into msg_table (id,sender_id,reciver_id,message) values (2,'test1','yassine','cv hmd');

insert into msg_table (id,sender_id,reciver_id,message) values (3,'yassine','test3','ach khebarek');
insert into msg_table (id,sender_id,reciver_id,message) values (4,'test3','yassine','hmd');

insert into msg_table (id,sender_id,reciver_id,message) values (5,'test2','test1','manzakin');
insert into msg_table (id,sender_id,reciver_id,message) values (6,'test1','test2','hayi');


create table accounts(
    username varchar2(20) PRIMARY KEY,
    email varchar2(40),
    password VARCHAR2(20) not null,
    avatar varchar2(80) default ''
);

alter table accounts add constraint email_check check(lower(email) like '%gmail.com');

insert into accounts  values ('yassine','yassine@gmail.com', 'yassine','');
insert into accounts  values ('W2sT7A','dtombs0@gmail.com', 'W2sT7A','');
insert into accounts  values ('test1','rszymczyk1@gmail.com', 't8WMQl1TpNd','');
insert into accounts  values ('test2','gklimecki2@gmail.com', 'QufksGftI','');
insert into accounts  values ('test3','ldemoge3@gmail.com', 'WB24tc','');
insert into accounts  values ('test4','sboulde4@gmail.com', 'C22nBinI','');
commit ;

--select * from msg_table where (lower(reciver_id)='test1' and lower(sender_id)='yassine') or (lower(sender_id)='test1' and lower(reciver_id)='yassine');