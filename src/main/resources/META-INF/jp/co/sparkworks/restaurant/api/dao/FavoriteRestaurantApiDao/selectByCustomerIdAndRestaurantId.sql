select
/*%expand*/*
FROM
favorite 
where 
favorite.customer_id=/*customerId*/'customerId'
AND
favorite.restaurant_id=/*restaurantId*/'restaurantId'

