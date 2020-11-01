create schema company;

show search_path;

set search_path to company;

create table departments(
	dept_id SERIAL primary key,
	dept_name VARCHAR(50),
	monthly_budget numeric(7,2) -- 99999.99
);

create table employees(
	empl_id SERIAL primary key,
	empl_name VARCHAR(50),
	monthly_salary numeric(6,2),
	empl_position VARCHAR(50),
	manager_id INTEGER references employees(empl_id),
	dept_id INTEGER references departments(dept_id)
);

insert into departments (dept_name, monthly_budget) values ('HR', 5000);
insert into departments (dept_name, monthly_budget) values ('Accounting', 10000), ('Fun', 15000);

insert into employees (empl_name, monthly_salary, empl_position, manager_id, dept_id) values
('Anthony', 9999, 'Chief fun officer', null, 3),
('Brittney', 9999.1, 'Something boring', null, 2),
('Bakhtawar', 2000, 'HR Director', null, 1);

insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Abbie', 3872, 'Arctic ground squirrel', 1, 1);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Justinian', 3438, 'Heron, striated', 2, 3);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Brynn', 3561, 'Thomson''s gazelle', 1, 3);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Marve', 1086, 'Blue waxbill', 3, 1);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Alicea', 4692, 'Phalarope, northern', 3, 2);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Dianemarie', 3284, 'Huron', 1, 3);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Bartie', 1468, 'Red-billed tropic bird', 3, 2);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Bartlet', 524, 'Nighthawk, common', 1, 3);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Doll', 4709, 'Partridge, coqui', 1, 2);
insert into employees (empl_name,  monthly_salary,  empl_position,  manager_id,  dept_id) values ('Donielle', 3423, 'Macaque, rhesus', 1, 1);


-- Inner join
select employees.empl_name as name, departments.dept_name as department
from employees
join departments
on employees.dept_id = departments.dept_id;


-- Left join
select employees.empl_name as name, departments.dept_name as department
from employees
left outer join departments
on employees.dept_id = departments.dept_id;

-- Right join
select employees.empl_name as name, departments.dept_name as department
from employees
right join departments
on employees.dept_id = departments.dept_id;

-- Full join
select employees.empl_name as name, departments.dept_name as department
from employees
full outer join departments
on employees.dept_id = departments.dept_id;


-- Self join
select e.empl_name "Employee", m.empl_name "Manager"
from employees e
join employees m
on e.manager_id = m.empl_id;

select *
from employees e
join employees m
on e.manager_id = m.empl_id;

-- User defined function
create function add(num1 INTEGER, num2 INTEGER)
returns integer
language plpgsql
as $$
declare 
	sum_result integer;
begin
		sum_result = num1 + num2;
		return sum_result;
end
$$

select add(4,5);

drop function add;

create or replace function increase_budget(increase_amount departments.monthly_budget%type, dept_id_input departments.dept_id%type)
returns void
language plpgsql
as $$
begin 
	update departments 
	set monthly_budget = monthly_budget + increase_amount
	where dept_id =dept_id_input;
end
$$

select increase_budget(500,1);

create or replace function increase_budget2(increase_amount departments.monthly_budget%type, dept_id_input departments.dept_id%type)
returns void
language plpgsql
as $$
declare 
	dept_budget_temp departments.monthly_budget %type;
begin
	select monthly_budget into dept_budget_temp 
	from departments 
	where dept_id = dept_id_input;

	if dept_budget_temp > 12000 then
	update departments 
	set monthly_budget = monthly_budget + increase_amount
	where dept_id =dept_id_input;
	end if;
end
$$

select increase_budget2(10000, 1);
select increase_budget2(10000, 3);

--**PRIZE** 
-- give prize (% of salary) for each employee in department 
--prize(the name of department, percentage of salary)
create or replace function prize(name_of_dept departments.dept_name%type, percent_of_prize INTEGER)
returns void
language plpgsql
as $$
begin
	update company.employees
	set monthly_salary = (monthly_salary/100)* percent_of_prize + monthly_salary
	where dept_id = (select dept_id 
	from company.departments 
	where dept_name = name_of_dept);
end
$$

select prize('HR', 50);


--*************TCL****************

begin * from departments;
	insert into department (dept_name, monthly_budget) values ('Something New', 5000);
	insert into department (dept_name, monthly_budget) values ('Something New1', 5000000);
commit;
end 


