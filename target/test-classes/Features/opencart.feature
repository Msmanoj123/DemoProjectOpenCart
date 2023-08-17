Feature: Opencart login module
Background: 
Given user is open the browser and pass url "https://awesomeqa.com/ui/"

@validdata
Scenario Outline: Valdate the login features with valid credentials
When user is click on login page
And Pass Uname <UN> And Password <PWD>
And click on login button
Then validate user is myaccount page
Examples: 
|UN              |PWD|
|manojms@gmail.com|msmanoj|
|deekshithgowda9897@gmail.com|Blackfield@562123|
|wrongpassword@gmail.com|wrongpassword|

@invaliddata
Scenario Outline: Valdate the login features with valid credentials
When user is click on login page
And Pass Uname <UN> And Password <PWD>
And click on login button
Then check proper error msg is displayed
Examples: 
|UN              |PWD|
|wrongpassword@gmail.com|wrongpassword|
|deekshithgowda9897|Blackfield@562123|
|manojms@gmail.com|wrongpassword|
|manojms@gmail.com|msmanoj|


@cart
Scenario: validate the cart feature
When login with "manojms@gmail.com" and "msmanoj"
When Add "Nikon D300" item to cart
Then valid the "Nikon D300" item is present in cart

@order
Scenario: validate the order feature
When login with "manojms@gmail.com" and "msmanoj"
And add iphone to cart
And click on checkout
And enter the require data for all the fields and order 
Then check item is ordered
