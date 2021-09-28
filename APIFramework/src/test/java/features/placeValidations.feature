Feature: Validating Place APIs

@AddPlace @Regression
Scenario Outline: Verify if place is added successfully using AddPlace API

Given AddPlace payload with "<name>"	"<langauge>"	"<address>"
When user calls "AddPlaceAPI" API with "POST" HTTP request
Then the API call is success with status code 200
And "status" response is "OK"
And verify place_id created maps to "<name>" using "getPlaceAPI" 
 

Examples: #data sets

|name					|langauge	|address				|
|my sweet home|english	|Michelson drive|
#|my new home	|marathi	|floyd drive		|

@DeletePlace @Regression
Scenario: To verify if deletePlace is working

Given DeletePlace payload
When user calls "deletePlaceAPI" API with "POST" HTTP request
Then the API call is success with status code 200
And "status" response is "OK"
