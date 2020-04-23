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
b_number integer not null auto_increment,
location varchar(50) not null,
name varchar(50),
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
priority varchar(3) not null,
zone integer(1) not null,
weight decimal(3,1),
rate decimal(4,2),
primary key (price_code),
foreign key (zone) references zone (zone_id)
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