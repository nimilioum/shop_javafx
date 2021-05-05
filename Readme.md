# Bank project
 This project is written as a university assignment.

---------------------
###Authors :

- Nima Saei
- Tohid Rostampour Tabrizi
- Fateme Hashemzade
- AmirHossein Maali
---------------------
###Dependencies
 
Dependencies are handled by maven.

---------------------------

### Database Config :

 - You can create a user in the mysql database like this:
    
    ```
   create user shop@localhost identified by 'shop';
   
   grant ALL PRIVILEGES on shop.* to shop@localhost;
    ```
 - If you want to have different user credentials, enter those
    
    credentials in dbConfig.java file.

------------------------------------
### Default credentials :

- **Admin** :  admin:admin

------------------------------------