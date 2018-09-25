select
  coupon.coupon_id
  , coupon.title
  , coupon.detail
  , coupon.end_date
  , restaurant.restaurant_id
  , restaurant.restaurant_name
  , restaurant.address
  , restaurant.telephone_phone
  , restaurant.business_hours
  , restaurant.image_url
  , restaurant.site_url
  , restaurant.latitude
  , restaurant.longitude 
FROM
restaurant LEFT JOIN coupon 
    on restaurant.restaurant_id = coupon.restaurant_id 

