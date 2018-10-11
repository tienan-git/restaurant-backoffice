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
      lottery_application.lottery_id =lottery.lottery_id
  ) count
  , A.lottery_application_status 
FROM
  lottery 
  left join 
  ( select 
  lottery_application.lottery_id 
  ,lottery_application.lottery_application_status
  from 
  lottery_application 
  inner join customer 
  on customer.customer_id = lottery_application.customer_id 
  and customer.device_id = /*deviceId*/'123'  ) A 
  on lottery.lottery_id = A.lottery_id 
WHERE
  now() between lottery.display_start_datetime and lottery.display_end_datetime
