select
  lottery_id
  , lottery_detail
  , lottery_title
  , lottery_image_url
  , lottery_image
  , end_datetime
  , announcement_datetime
  , ( 
    select
      count(*) 
    from
      lottery_application 
    where
      lottery_application.lottery_id = lottery_id
  ) count 
FROM
  lottery 
WHERE
  now() between start_datetime and end_datetime
