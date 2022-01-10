Feature: logging in as an employee

Background: Given the employee is on TRMS home page

Scenario Outline: logging in with correct credentials
	Given the employee is on the TRMS home page
	And the user clicks the Log In link
	When the user enters "<username>" and "<password>" to log in
	And the user clicks the login button
	Then the page says "<username>"
	
	Examples:
		|		username		|		password				|
		|		vwraggs2		|		zmhu6mvJ56CY		|
		|		nbonus3			|		Nu3f71S					|
		
Scenario Outline: logging in with incorrect passwords
	Given the user is on the TRMS home page
	And the user clicks the Log In link
	When the user enters "<username>" and "<password>" to log in
	And the user clicks the login button
	Then the page says Incorrect Credentials
	
	Examples:
		|		username		|		password		|
		|		megan				|		erfbh				|
		|		tom12				|		tyrhuf			|
		|		fponce			|		ilikedogs12	|