SELECT
  coupon_id
  ,  restaurant_id
  , title
  , detail
  , start_date
  , end_date
  , total_amount
  , ( 
    select
      count(*) 
    from
     coupon_hold
    where
    coupon_hold.coupon_id = coupon_id
  ) count 
FROM
coupon
	

WHERE
coupon.is_actived = 1
   
	/*%if  couponSearchDto.couponId != null && couponSearchDto.couponId.length() != 0 */
    and coupon.coupon_id = /* couponSearchDto.couponId */'1'
   /*%end*/

  