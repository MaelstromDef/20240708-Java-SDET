-- DATA TYPES --
/*
SERIAL

CHAR
VARCHAR
TEXT

INTEGER
SMALLINT
BIGINT
DECIMAL or DEC
FLOAT

BOOLEAN or BOOL

DATE
TIME
TIMESTAMP
*/

SELECT * FROM album;

SELECT * FROM genre WHERE genre_id = 2;

-- AND OPERATOR --
SELECT * FROM track
WHERE genre_id = 2 AND milliseconds > 300000;

-- ORDER BY -- 
SELECT * FROM track
WHERE genre_id = 2 AND milliseconds > 300000
ORDER BY album_id DESC;

-- Built-in Function --
SELECT MAX(unit_price) as max_total
FROM track;

SELECT MIN(unit_price) as min_total
FROM track;

SELECT AVG(unit_price) as avg_price
FROM track;

SELECT COUNT(unit_price) as count_price
FROM track;

SELECT SUM(unit_price) as sum_total
FROM track;

SELECT first_name, last_name FROM customer
WHERE country = 'USA';

-- JOINS --

-- Create a query that will show invoices of customers who are from Brazil.
--  We want the resulting table to display the customers' full name, invoice ID,
--  invoice date, and billing country.

SELECT c.first_name, c.last_name, c.country, i.invoice_id, i.invoice_date, i.billing_country 
FROM customer as c, invoice as i
WHERE c.country = 'Brazil' AND c.customer_id = i.customer_id
ORDER BY c.first_name;

-- Create a query that will return invoices with the associated sales agent.
--  The resulting table to include the sales agent's full name.

-- Each record individually
SELECT e.first_name as "Employee First", e.last_name as "Employee Last",
        i.invoice_id as "Invoice ID", i.customer_id as "Customer ID"
FROM customer as c 
JOIN invoice as i
ON c.customer_id = i.customer_id
JOIN employee as e
ON e.employee_id = c.support_rep_id
ORDER BY e.last_name;

-- Grouped records
SELECT (e.first_name || ' ' || e.last_name) as "Employee", COUNT(c.customer_id) as "Customers Helped"
FROM customer as c 
JOIN invoice as i
ON c.customer_id = i.customer_id
JOIN employee as e
ON e.employee_id = c.support_rep_id
GROUP BY "Employee"
ORDER BY "Employee";

-- Create a query that will include purchased tracks and artist names
SELECT i.*, t.name as track_name, ar.name as artist_name
FROM invoice_line as i
JOIN track as t ON i.track_id = t.track_id
JOIN album as a ON t.album_id = a.album_id
JOIN artist as ar ON a.artist_id = ar.artist_id;

-- Create a query that will find what sales agent made the most sales 2021
SELECT e.*, top_employee.total_sales_amt
FROM
(
        SELECT *
        FROM
        (
                SELECT (e.first_name || ' ' || e.last_name) as employee_name, SUM(i.total) as total_sales_amt
                FROM customer as c 
                JOIN invoice as i
                ON c.customer_id = i.customer_id
                JOIN employee as e
                ON e.employee_id = c.support_rep_id
                WHERE i.invoice_date BETWEEN '2021-01-01' AND '2021-12-31'
                GROUP BY employee_name
        ) as sales_table
        ORDER BY total_sales_amt DESC LIMIT 1
) as top_employee
JOIN employee as e on CONCAT(e.first_name, ' ', e.last_name) = top_employee.employee_name;

SELECT (e.first_name || ' ' || e.last_name) as employee_name, SUM(i.total) as total_sales_amt
FROM customer as c 
JOIN invoice as i
ON c.customer_id = i.customer_id
JOIN employee as e
ON e.employee_id = c.support_rep_id
WHERE i.invoice_date BETWEEN '2021-01-01' AND '2021-12-31'
GROUP BY employee_name
ORDER BY total_sales_amt DESC LIMIT 1;