use project;

use project;

set FOREIGN_KEY_CHECKS = 0;

drop table if exists person;
create table if not exists person
(
ssn varchar(9) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
m_init char,
DOB date not null,
address varchar(50) not null,
phone varchar(14) not null,
sex char,
primary key(ssn),
unique(phone)
);

drop table if exists employee;
create table if not exists employee
(
employee_id integer not null auto_increment,
ssn varchar(9) not null,
salary integer(9) not null,
manager_id integer(9),
department_num integer(3) not null,
building_num integer (5) not null,
primary key(employee_id),
foreign key (department_num) references department (d_num),
foreign key (building_num) references building (b_number),
foreign key (ssn) references person (ssn)
);

drop table if exists customer;
drop table if exists client;
create table if not exists customer
(
account_num integer not null auto_increment,
client_type char not null,
loyality_level int(2),
email varchar(30),
ssn varchar(9),
primary key(account_num),
foreign key (ssn) references person (ssn)
);

drop table if exists department;
create table if not exists department
(
d_num integer not null auto_increment,
department_name varchar(20) not null,
HQ_location_num integer,
manager_id integer not null,
primary key (d_num),
foreign key (HQ_location_num) references building (b_number),
foreign key (manager_id) references employee (employee_id)
);

drop table if exists building;
create table if not exists building
(
b_number integer not null,
location varchar(50) not null,
name varchar(10),
primary key (b_number)
);

drop table if exists store;
create table if not exists store
(
store_id integer not null,
zone_id integer not null,
owning_dep_num integer not null,
b_number integer not null,
primary key (store_id),
foreign key (zone_id) references zone (zone_id),
foreign key (owning_dep_num) references department (d_num),
foreign key (b_number) references building (b_number)
);

drop table if exists warehouse;
create table if not exists warehouse
(
warehouse_id integer not null,
capacity integer(9),
owning_dep_num integer not null,
b_number integer not null,
primary key (warehouse_id),
foreign key (owning_dep_num) references department (d_num),
foreign key (b_number) references building (b_number)
);

drop table if exists zone;
create table if not exists zone
(
zone_id integer not null auto_increment,
country_id integer not null,
postal_code_low integer(9),
postal_code_high integer(9),
primary key (zone_id),
foreign key (country_id) references country (country_id)
);

drop table if exists country;
create table if not exists country
(
country_id integer not null auto_increment,
name_of_country varchar(20) not null,
state_type varchar(10),
abbr varchar(3) unique not null,
primary key (country_id)
);

drop table if exists currency;
create table if not exists currency
(
currency_id integer  not null auto_increment,
name varchar(20) not null,
abbr varchar(3) not null,
conversion_rate_to_US decimal(10,6) not null,
country_code varchar(2) not null,
primary key (currency_id),
foreign key (country_code) references country (abbr)
);

drop table if exists tax_custom;
create table if not exists tax_custom
(
tax_code integer not null auto_increment,
restrictions bool not null,
internation bool not null,
percentage decimal (3,2) not null,
country_code varchar(2) not null,
primary key (tax_code),
foreign key (country_code) references country(abbr)
);

drop table if exists shipment;
create table if not exists shipment
(
shipment_id integer not null auto_increment,
priority int(1) not null,
is_complete bool not null,
completion_time datetime,
last_warehouse integer not null,
last_route integer,
is_in_transit bool not null,
requester varchar(9) not null,
primary key (shipment_id),
foreign key (last_warehouse) references warehouse (warehouse_id),
foreign key (last_route) references route (route_id)
);

drop table if exists rate;
create table if not exists rate
(
price_code integer not null auto_increment,
origin varchar(9) not null,
destination varchar(9) not null,
priority integer(1) not null,
percentage decimal(4) not null,
tax_code integer not null,
weight decimal(4,3),
rate decimal(6,4),
currency_type integer not null,
primary key (price_code),
foreign key (tax_code) references tax_custom (tax_code),
foreign key (currency_type) references currency (currency_id)
);

drop table if exists route;
create table if not exists route
(
route_id integer not null auto_increment,
origin varchar(9) not null,
destination varchar(9) not null,
route_name varchar(9),
distance decimal(9),
time_estimate int(5),
zone integer not null,
vehicle_id integer not null,
primary key (route_id),
foreign key (zone) references zone (zone_id),
foreign key (vehicle_id) references vehicle (vehicle_id)
);

drop table if exists vehicle;
create table if not exists vehicle
(
vehicle_id integer not null auto_increment,
transport_type varchar(4) not null,
mpg integer(4),
max_range integer(6),
stored_warehouse_num integer not null,
primary key (vehicle_id),
foreign key (stored_warehouse_num) references warehouse (warehouse_id)
);

drop table if exists package;
create table if not exists package
(
package_id integer not null auto_increment,
package_type varchar(4) not null,
weight decimal(5) not null,
insured bool not null,
accessorial varchar(3),
shipment_id integer not null,
is_shipped boolean not null,
is_complete boolean not null,
client_number integer not null,
price_code integer not null,
store_processed integer not null,
primary key (package_id),
foreign key (shipment_id) references shipment (shipment_id),
foreign key (client_number) references customer (account_num),
foreign key (price_code) references rate (price_code),
foreign key (store_processed) references store (store_id)
);

drop table if exists insurance;
create table if not exists insurance
(
insurance_id integer not null auto_increment,
transaction_id integer not null,
name_of_insurance varchar (50) not null,
amount decimal(8) not null,
type_of_insurance varchar(5) not null,
primary key (insurance_id),
foreign key (transaction_id) references package (package_id)
);

drop table if exists state;
create table if not exists state
(
state_id varchar(2),
state_name varchar(25),
start_postal integer(9),
end_postal integer(9),
country_id  integer,
primary key (state_id),
foreign key(country_id) references country(country_id)
);
drop table if exists accessorial;
create table if not exists accessorial
(
acc_id integer not null auto_increment,
acc_code varchar(3) not null,
acc_name varchar(50) not null,
service varchar(3) not null,
flat_rate decimal(5,3),
primary key (acc_id, acc_code) 
);

set FOREIGN_KEY_CHECKS = 1;

insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'DDO', 'Direct Delivery Only', '1DM', 5.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'DDO', 'Direct Delivery Only', '2DM', 4.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'DDO', 'Direct Delivery Only', '3DM', 3.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'DDO', 'Direct Delivery Only', '4DM', 2.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'DDO', 'Direct Delivery Only', '5DM', 1.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RPS', 'Rural Pickup Surcharge', '1DM', 7.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RPS', 'Rural Pickup Surcharge', '2DM', 6.5000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RPS', 'Rural Pickup Surcharge', '3DM', 5.5000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RPS', 'Rural Pickup Surcharge', '4DM', 3.5000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RPS', 'Rural Pickup Surcharge', '5DM', 3.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RES', 'Residential Surcharge', '1DM', 5.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RES', 'Residential Surcharge', '2DM', 4.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RES', 'Residential Surcharge', '3DM', 3.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RES', 'Residential Surcharge', '4DM', 2.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'RES', 'Residential Surcharge', '5DM', 1.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'SLP', 'Peak Surcharge', '1DM', 5.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'SLP', 'Peak Surcharge', '2DM', 4.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'SLP', 'Peak Surcharge', '3DM', 3.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'SLP', 'Peak Surcharge', '4DM', 2.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'SLP', 'Peak Surcharge', '5DM', 1.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'FSC', 'Fuel Surcharge', '1DM', 5.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'FSC', 'Fuel Surcharge', '2DM', 4.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'FSC', 'Fuel Surcharge', '3DM', 3.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'FSC', 'Fuel Surcharge', '4DM', 2.0000);
insert into accessorial(acc_code,acc_name,service,flat_rate) values( 'FSC', 'Fuel Surcharge', '5DM', 1.0000);

set FOREIGN_KEY_CHECKS = 0;

insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Dirham', 'AED', 0.272257, 'AE');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Afghani', 'AFA', 0.278888, 'AF');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Dollar', 'USD',  1.000000, 'AR');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Dollar', 'AUD', 0.556223, 'AU');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Taka', 'BDT', 0.011773, 'BD');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Euro', 'EUR', 1.081400, 'BE');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Boliviano', 'BOB', 0.144872, 'BO');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Real', 'BRL', 0.186849, 'BR');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Dollar', 'CAD', 0.703037, 'CA');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Franc', 'CHF', 1.023646, 'SW');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Yuan', 'RMB', 0.121017, 'CN');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Rupee', 'INR', 0.013127, 'IN');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Yen', 'JPY', 0.009226, 'JP');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Peso', 'MXN', 0.398746, 'MX');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Rupee', 'NPR', 0.008271, 'NP');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Peso', 'PHP', 0.0119729, 'PH');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Pound', 'GBP', 1.223300, 'GB');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Ruble', 'RUB', 0.013072, 'RU');
insert into currency(name,abbr,conversion_rate_to_US, country_code) values ('Peso', 'CLP', 0.001155, 'CL');


insert into country(name_of_country,state_type, abbr) values('United Arabs', null, 'AE');
insert into country(name_of_country,state_type, abbr) values('Argentina', null, 'AR');
insert into country(name_of_country,state_type, abbr) values('Australia', null, 'AU');
insert into country(name_of_country,state_type, abbr) values('Belgium', null, 'BE');
insert into country(name_of_country,state_type, abbr) values('Brazil', null, 'BR');
insert into country(name_of_country,state_type, abbr) values('China', null, 'CN');
insert into country(name_of_country,state_type, abbr) values('Canada', null, 'CA');
insert into country(name_of_country,state_type, abbr) values('Germany', null, 'GE');
insert into country(name_of_country,state_type, abbr) values('Japan', null, 'JP');
insert into country(name_of_country,state_type, abbr) values( 'Nepal', null, 'NP');
insert into country(name_of_country,state_type, abbr) values( 'Mexico', null, 'MX');
insert into country(name_of_country,state_type, abbr) values( 'Phillipines', null, 'PH');
insert into country(name_of_country,state_type, abbr) values( 'Great Britain', null, 'GB');
insert into country(name_of_country,state_type, abbr) values( 'United States', 'state', 'US');

insert into department( department_name,HQ_location_num,manager_id)values ( 'Human Resource', 2, 10);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Shipping', 2, 9);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Billing', 1, 8);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Marketing', 4, 2);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Accounting', 4, 1);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Package Handling', 3, 3);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Janiter', 3, 8);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Research', 5, 5);
insert into department( department_name,HQ_location_num,manager_id)values ( 'Head Quarter', 5, 10);

insert into tax_custom(restrictions,internation,percentage,country_code) values( false, false, 1., 'AE');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.02, 'AR');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.02, 'AU');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.02, 'BE');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.12, 'BR');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.20, 'CN');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.16, 'CA');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.13, 'GE');
insert into tax_custom(restrictions,internation,percentage,country_code) values(false, true, 1.11, 'JP');
insert into tax_custom(restrictions,internation,percentage,country_code) values( false, true, 1.15, 'NP');
insert into tax_custom(restrictions,internation,percentage,country_code) values( false, true, 1.08, 'MX');
insert into tax_custom(restrictions,internation,percentage,country_code) values( false, true, 1.07, 'PH');
insert into tax_custom(restrictions,internation,percentage,country_code) values( false, true, 1.04, 'GB');
insert into tax_custom(restrictions,internation,percentage,country_code) values( false, true,1.06, 'US');

insert into zone(country_id,postal_code_low,postal_code_high) values(14,20000,26899);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,01000,14999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,50000,62999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,27000,31999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,96700,99999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,80000,89899);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,32000,39799);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,15000,19999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,40000,49999);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,63000,71499);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,71600,73399);
insert into zone(country_id,postal_code_low,postal_code_high) values(14,90000,96199);
insert into zone(country_id,postal_code_low,postal_code_high) values(1,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(2,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(3,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(4,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(5,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(6,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(7,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(8,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(9,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(10,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(11,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(12,null,null);
insert into zone(country_id,postal_code_low,postal_code_high) values(13,null,null);

