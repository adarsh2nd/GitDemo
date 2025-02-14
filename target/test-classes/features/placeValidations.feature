Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using Add Place API
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

    Examples:
    |name|language|address                        |
    |Tom |English |Park Street,Kolkata,Pin- 897657|
    #|Harry|Hindi |Film City Hyderabad,Pin-87643|
    
    @DeletePlace
   Scenario: Verify if Delete place functionality is working
   Given Delete place payload
   When user calls "DeletePlaceAPI" with "Post" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"