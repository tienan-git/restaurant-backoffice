select
  lottery.lottery_id
  , lottery.lottery_detail
  , lottery.lottery_title
  , lottery.lottery_image_url
  , lottery.end_datetime
  , lottery.announcement_datetime
  , ( 
    select
      count(*) 
    from
      lottery_application 
    where
      lottery_application.lottery_id = lottery_id
  ) count
  , lottery_application.lottery_application_status 
FROM
  lottery join lottery_application 
    on lottery.lottery_id = lottery_application.lottery_id join customer 
    on customer.customer_id = lottery_application.customer_id 
    and customer.device_id = /*deviceId*/'123' 
WHERE
  now() between start_datetime and end_datetime
