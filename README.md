# SUPTRIP #

## API ##

	GET api/trips

Returns a list of trips:

    [
		{
			"id": (int),
			"departureTime": (int),
			"arrivalTime": (int),
			"price": (float)
			"origin": {
      			"name": (string),
      			"id": (int)
    		},
			"destination": {
      			"name": (string),
      			"id": (int)
    		}
		},
		[...]
	]


----------


    GET api/trips/:campusName

Returns a list of trips FROM or TO the searched campus:

    [
		{
			"id": (int),
			"departureTime": (int),
			"arrivalTime": (int),
			"price": (float)
			"origin": {
      			"name": (string),
      			"id": (int)
    		},
			"destination": {
      			"name": (string),
      			"id": (int)
    		}
		},
		[...]
	]


----------

	GET api/users

Returns a list of users:

    [
    	{
    		"id": (int),
    		"firstName": (string),
    		"lastName": (string),
    		"email": (string),
    		"password": (string),
    		"admin": (bool),
    		"campus": {
    			"id": (int),
    			"name": (string)
    		}
    	},
    	[...]
	]


----------

	GET api/campuses

Returns a list of campuses:

    [
    	{
    		"id": (int),
    		"name": (string)
    	},
    	[...]
	]


----------


