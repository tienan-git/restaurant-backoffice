select
  lottery.lottery_title
  , lottery.lottery_detail
  , lottery_application.lottery_application_status
  , lottery_application.apply_datetime 
FROM
  lottery_application JOIN lottery 
    on lottery_application.lottery_id = lottery.lottery_id JOIN customer 
    on lottery_application.customer_id = customer.customer_id 
where
  customer.device_id = /*deviceId*/'deviceId'
