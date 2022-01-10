Feature: get requests by requestor


Scenario: viewing the requests by the employee id
	Given user is on the page
	When employee id is entered
	Then table shows the requests for the employee