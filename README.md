# SUPTRIP #

## API ##

	GET /trips

Returns a list of trips:

    [
		{
			"id": (int),
			"departure": (int),
			"arrival": (int),
			"duration": (int)
		},
		[...]
	]


----------


    GET /trips/:name

Returns a trip if found:

    {
		"id": (int),
		"departure": (int),
		"arrival": (int),
		"duration": (int)
	}


----------




