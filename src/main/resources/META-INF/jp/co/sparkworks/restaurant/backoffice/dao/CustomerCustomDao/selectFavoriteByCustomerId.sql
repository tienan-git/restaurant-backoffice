SELECT
  restaurant.restaurant_id
  , restaurant.restaurant_name
  ,favorite.create_datetime
FROM
favorite JOIN restaurant
    on  restaurant.restaurant_id =  favorite.restaurant_id
WHERE
  favorite.is_actived = 1 

  and favorite.customer_id = /*customerId */'1' 
