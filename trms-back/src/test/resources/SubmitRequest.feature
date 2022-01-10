Feature: submitting a request


Scenario: logged in employee can submit a reimbursement request
	Given the user logged in
	When data is entered and 
	And the submit button pressed
	Then request has been submitted