# Spot Task

This application was generated in spring.io with some basic dependency

# Development

There is some set of fix data of user which will be used to 
create card API request.
``Use 1,2,3,4 as spot_user_id while creating credit card``

# About Block country
There are two APIs to make block country configurable, thought that it will be managed by Admin panel
1. Add Block country
2. Delete Block country

As per the exercise purpose I have not created update API for block country

I have used below sites to test the credit cards
`https://support.bluesnap.com/docs/test-credit-card-numbers`

# Database
For this Application postgreSQL database is used.

# Junit
For Junit test cases for the exercise purpose I have written some test cases, not cover up all things just to show you 
1. BlockCountryServiceTest
2. CreditCardServiceTest

# Cron
CronService class method processCreditCards will run at every 30 seconds

# Bin list via WebClient
MyService class will responsible for web client and call 3rd party API

# Postman Collection link
https://www.getpostman.com/collections/b8c268855f70062281e0