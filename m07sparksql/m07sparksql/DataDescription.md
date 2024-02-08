Expedia data. Expedia has provided you logs of customer behavior. These include what customers searched for, how they interacted with search results (click/book), whether or not the search result was a travel package. This data is a random selection from Expedia and is not representative of the overall statistics.

| Column name | Description | Data type |
| --- | --- | --- |
| id | ID of a data record | long |
| date | time | Timestamp | string |
| site | name | ID of the Expedia point of sale (i.e. Expedia.com, Expedia.co.uk, Expedia.co.jp, ...) | integer |
| posa | continent | ID of continent associated with site | name | integer |
| user | location | country | The ID of the country the customer is located | integer |
| user | location | region | The ID of the region the customer is located | integer |
| user | location | city | The ID of the city the customer is located | integer |
| orig | destination | distance | Physical distance between a hotel and a customer at the time of search. A null means the distance could not be calculated | double |
| user | id | ID of user | integer |
| is | mobile | 1 when a user connected from a mobile device, 0 otherwise | integer |
| is | package | 1 if the click/booking was generated as a part of a package (i.e. combined with a flight), 0 otherwise | integer |
| channel | ID of a marketing channel | integer |
| srch | ci | Checkin date | string |
| srch | co | Checkout date | string |
| srch | adults | cnt | The number of adults specified in the hotel room | integer |
| srch | children | cnt | The number of (extra occupancy) children specified in the hotel room | integer |
| srch | rm | cnt | The number of hotel rooms specified in the search | integer |
| srch | destination | id | ID of the destination where the hotel search was performed | integer |
| srch | destination | type | id | Type of destination | integer |
| hotel | id | ID of hotel | long |

Weather-Hotels data joined by 4-characters geohash.

| Column name | Description | Data type | Partition |
| --- | --- | --- | --- |
| address | Hotel address | string | no |
| avg_tmpr_c | Average temperature in Celsius | double | no |
| avg_tmpr_f | Average temperature in Fahrenheit | double | no |
| city | Hotel city | string | no |
| country | Hotel country | string | no |
| geoHash | 4-characters geohash based on Longitude & Latitude | string | no |
| id | ID of hotel | string | no |
| latitude | Latitude of a weather station | double | no |
| longitude | Longitude of a weather station | double | no |
| name | Hotel name | string | no |
| wthr_date | Date of observation (YYYY-MM-DD) | string | no |
| wthr_year | Year of observation (YYYY) | string | yes |
| wthr_month | Month of observation (MM) | string | yes |
| wthr_day | Day of observation (DD) | string | yes |
