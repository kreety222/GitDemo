Feature: Validating Place API
@AddPlace @Regression
	Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
			Given Add Place Payload with "<name>" "<language>" "<address>"
			When user calls "AddPlaceAPI" with "Post" http request
			Then the API call got success with status code 200
			And "status" in response body is "OK"
			And verify place_Id created maps to "<name>" using "getPlaceAPI"
				
				
	Examples: 
      | name  | language   |address  |
      | dasjk |     English| WTC     |
 #  | letitbe |  American| california  |
 
@DeletePlace @Regression
 Scenario: Verify if delete place functionality is working
 	Given DeletePlace Payload
 	When user calls "deletePlaceAPI" with "Post" http request
 	Then the API call got success with status code 200
 	And "status" in response body is "OK"
 