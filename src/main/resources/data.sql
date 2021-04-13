drop table employee;

create table employee
(
   employee_id bigint AUTO_INCREMENT not null,
   name varchar(255) not null,
   email_id varchar(255) not null
);

insert into employee (email_id, name) values ('rishabh.jain@gmail.com', 'Rishabh Jain');
insert into employee (email_id, name) values ('priya.jain@gmail.com', 'Priya Jain');
insert into employee (email_id, name) values ('megha.jain@gmail.com', 'Megha Jain');
insert into employee (email_id, name) values ('satyam.jain@gmail.com', 'Satyam Jain');
insert into employee (email_id, name) values ('saral.jain@gmail.com', 'Saral Jain');
insert into employee (email_id, name) values ('rahul.jain@gmail.com', 'Rahul Jain');
insert into employee (email_id, name) values ('akshay.jain@gmail.com', 'Akshay Jain');