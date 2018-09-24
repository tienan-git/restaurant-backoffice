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
  coupon_hold JOIN coupon 
    on coupon_hold.coupon_id = coupon.coupon_id JOIN customer 
    on customer.customer_id = coupon_hold.customer_id JOIN restaurant 
    on restaurant.restaurant_id = coupon.restaurant_id 
WHERE
  customer.device_id = /*deviceId*/'123'
