Feature: validating place API

@AddPlace
Scenario Outline: verify if place is being successfully added using AddAPI
	Given add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
|name  | language | address     |
|A-vila| English  | Pune				|
#|B-vila| Marathi  | Mumbai			|

@DeletePlace
Scenario: if delete place functionality is working
	Given DeletePlace PayLoad 
	When user calls "deletePlaceAPI" with "Post" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"