# MidtermBankingSystem

:bank:
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 
:bank: 


This project is a banking system.



## FOR USE

Create de next database:
~~~
DROP SCHEMA midterm;

CREATE SCHEMA IF NOT EXISTS midterm;

USE midterm;

CREATE TABLE user_table (
    id BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255), 
    PRIMARY KEY (id)
);

CREATE TABLE role_table (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user_table (id)
);

CREATE TABLE IF NOT EXISTS admin_table (
id BIGINT,
name VARCHAR (255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user_table (id)
);

CREATE TABLE IF NOT EXISTS third_party (
id BIGINT AUTO_INCREMENT,
name VARCHAR (255),
hashed_key VARCHAR (255),
PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account_holder (
id BIGINT,
first_name VARCHAR (255),
date_of_birth DATE,
mailing_address VARCHAR (255),
street VARCHAR (255),
building_number INT,
floor INT,
door VARCHAR (255),
zip_code INT,
city VARCHAR (255),
country VARCHAR (255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user_table (id)
);

CREATE TABLE IF NOT EXISTS account_table (
id BIGINT NOT NULL UNIQUE,
balance_amount DECIMAL,
balance_currency VARCHAR(255),
secret_key VARCHAR(255),
primary_owner BIGINT,
secondary_owner BIGINT,
status VARCHAR(255),
creation_date DATE DEFAULT (current_date()),
PRIMARY KEY (id),
FOREIGN KEY (primary_owner) REFERENCES account_holder (id),
FOREIGN KEY (secondary_owner) REFERENCES account_holder (id) 
);

CREATE TABLE IF NOT EXISTS checking (
id BIGINT,
date_maintenance_fee DATE,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account_table (id) 
);

CREATE TABLE IF NOT EXISTS credit_card (
id BIGINT,
credit_limit_amount DECIMAL,
credit_limit_currency VARCHAR(255),
interest_rate DECIMAL,
interest_add_date DATE,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account_table (id) 
);

CREATE TABLE IF NOT EXISTS saving (
id BIGINT,
interest_rate DECIMAL (19,6),
interest_add_date DATE,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account_table (id) 
);

CREATE TABLE IF NOT EXISTS student_checking (
id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account_table (id) 
);
~~~

Insert the admin user
(the password without encoder is "password"):
~~~
INSERT INTO user_table (username, password) 
VALUES ("ADMIN", "$2a$10$g2u/McjSiFmdHnotKzawxuJrc/9rd/5zWBsWHBXV3wraZKf/r0yK2");

INSERT INTO role_table (name, user_id) 
VALUES ("ADMIN", "1");

INSERT INTO admin_table (id, name) VALUES (1, "ADMIN");

~~~


## ADMIN
An user with Admin role can:

* Create another role:
    * **POST**  :green_square:        "/roles"

* Create a account holder:

    * **POST**  :green_square:       "/accountholders"
   
* Create a third party: 
    * **POST**  :green_square:        "/thirdparties"  
    
* Create the different accounts (saving account, credit card account, checking account (student checking account when de orimary owner is less than 24 years old)):
     
    * **POST**  :green_square:        "/savings"
    
    * **POST**  :green_square:       "/creditcards"
    
    * **POST**  :green_square:       "/checkings"

      
* View a list of account holders:
     * **GET**  :blue_square:      "/accountHolders"
     
* View a list of accounts with basic information:
     * **GET**  :blue_square:      "/accounts"
     
* View basic information from an account with the account id:
     * **GET**  :blue_square:      "/accounts{id}"
     
* Modify the status (active/frozen) of an account:
    *   **PATCH**  :purple_square:       "/accounts/{id}/update"
    
* Modify the balance of an account:
    *   **PATCH** :purple_square:       "/accounts/{id}/modifybalance"
    
* Apply the interest to a saving account:
    * **PATCH** :purple_square:        "/accounts/savinginterest"
    
* Apply the interest to a credit card account:
    * **PATCH** :purple_square:        "/accounts/creditcardinterest"
    
* Apply the mantenance fee to the account:
    * **PATCH** :purple_square:        "/accounts/maintenance"
    
* Apply the penalty fee to the account if the minimum balance drop:
    * **PATCH** :purple_square:       "/accounts/penaltyfee"
    
* Delete a third party:
    * **DELETE**  :red_square:      "/thirdparties/{id}/delete"
    
    
## OWNER
An user with owner role can:       
   
* View the balance of the account which is primary or secondary owner:
  * **GET**  :large_blue_circle:  "/accounts/{id}/balance"
  
* Make a transfer from the account where is primary or secondary owner:
  * **PATCH**  :purple_circle:  "/accounts/{id}/transfer"
  
* Modify the address:
  * **PUT**  :orange_circle:  "/accountHolders/{id}/address"
           
* Pay to a third party:
   * **PATCH**  :purple_circle:  "/thirdparties/{hashedKey}/sendmoney"

* Receive money from a third party:
   * **PATCH**  :purple_circle:  "/thirdparties/{hashedKey}/receivemoney"
  




