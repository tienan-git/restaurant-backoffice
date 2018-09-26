SELECT
  lottery_application.lottery_application_id
  , lottery_application.lottery_id
  , lottery_application.customer_id
  , lottery_application.lottery_application_status
  , lottery_application.apply_datetime
  , lottery_application.validity_flag
  , customer.device_id
  , customer.nick_name 
FROM
  lottery_application JOIN customer 
    on lottery_application.customer_id = customer.customer_id 
WHERE
  lottery_application.is_actived = 1 
/*%if  lotteryId != null */
  and lottery_application.lottery_id = /*lotteryId */'1' 
/*%end*/

  