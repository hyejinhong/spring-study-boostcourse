create table business_card (
name varchar(20) not null,
phone varchar(20) not null,
company_name varchar(30) not null,
create_date datetime not null,
primary key (name, phone)
)