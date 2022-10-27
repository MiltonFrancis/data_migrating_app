# Data Migrating App
### Simple  App that demonstrates the migration of data from one database to a next based on changes in one 

This application is a console based application that migrates data from one database to another based on changes in one. It uses the in memory database H2 to simulate the two databases. 

There are two databases: bank and bank_clone. Within each of these two databases is a customer table. When the application is launch there is a scheduler that runs a monitor function every 30 secs (for convenience instead of every 5 min). This function checks if a record is added to the customer table in the bank database or if an existing record has been modified. If a new record is added then a new record with same data is added to the customer table in bank_clone database. If an existing record that is in the customer table within the bank database was recently modified, (this is done by checking the lastModified fields of both the customer's records in the bank database and the bank_clone database) then the record is updated in the bank_clone database's customer table.  

NB. This Solution does not consider deletions. Therefore, if a record is deleted from the customer table in the bank database, then the identical record would still exist in the customer table of the bank_clone database as the program does not detect deletions. I was not sure if deletions should be treated with. I will also consider further optimization options.
