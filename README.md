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
  

            
