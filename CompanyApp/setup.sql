create schema company;

create table company.departments(
	dept_id SERIAL primary key,
	dept_name VARCHAR(50),
	monthly_budget numeric(7,2) -- 99999.99
);

insert into company.departments (dept_name, monthly_budget) values ('HR', 5000);
insert into company.departments (dept_name, monthly_budget) values ('Accounting', 10000), ('Fun', 15000);