select

   restaurant.restaurant_id
  , restaurant.restaurant_name
  , restaurant.address
  , restaurant.telephone_phone
  , restaurant.business_hours
  , restaurant.image_url
  , restaurant.site_url
  , restaurant.latitude
  , restaurant.longitude 
FROM
favorite  join  restaurant  on favorite.restaurant_id = restaurant.restaurant_id
where 
favorite.customer_id=/*customerId*/'customerId'

