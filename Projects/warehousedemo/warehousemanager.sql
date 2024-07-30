-- ADMINISTRATORS

DROP TABLE administrators;

CREATE TABLE administrators(
	id BIGSERIAL PRIMARY KEY,
	company_name VARCHAR(255),
	password VARCHAR(255)
);

ALTER TABLE administrators
	ALTER COLUMN company_name TYPE VARCHAR(255),
	ALTER COLUMN password TYPE VARCHAR(255);

SELECT * FROM administrators;

-- EMPLOYEE LOCATIONS

DROP TABLE employee_locations;

CREATE TABLE employee_locations(
	id BIGSERIAL PRIMARY KEY,
	employee_id integer,
	warehouse_id integer
);

-- EMPLOYEES

DROP TABLE employees;

CREATE TABLE employees(
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(255),
	last_name VARCHAR(255)
);

ALTER TABLE employees
	ALTER COLUMN first_name TYPE VARCHAR(255),
	ALTER COLUMN last_name TYPE VARCHAR(255);

-- ITEM_LOCATIONS

DROP TABLE item_locations;

CREATE TABLE item_locations(
	item_id integer,
	warehouse_id integer,
	quantity integer,
	CONSTRAINT item_locations_pkey PRIMARY KEY (item_id, warehouse_id)
);

-- ITEMS

DROP TABLE items;

CREATE TABLE items(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255)
);

ALTER TABLE items
	ALTER COLUMN name TYPE VARCHAR(255);

-- WAREHOUSES

DROP TABLE warehouses;

CREATE TABLE warehouses(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255),
	location VARCHAR(255),
	size integer,
	admin_id integer,
	CONSTRAINT admin_warehouse_fk_admin_id 
		FOREIGN KEY (admin_id) 
		REFERENCES administrators(id)
);

ALTER TABLE warehouses
	ALTER COLUMN name TYPE VARCHAR(255),
	ALTER COLUMN location TYPE VARCHAR(255);

SELECT * FROM warehouses;