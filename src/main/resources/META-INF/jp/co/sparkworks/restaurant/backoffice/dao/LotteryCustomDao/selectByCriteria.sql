SELECT
  lottery_id
  , lottery_title
  , lottery_detail
  , lottery_image_url
  , start_datetime
  , end_datetime
  , announcement_datetime
  , coupon_id
  , ( 
    select
      count(*) 
    from
      lottery_application 
    where
      lottery_application.lottery_id =lottery. lottery_id
  ) count 
FROM
lottery
	

WHERE
lottery.is_actived = 1
   
	/*%if  lotterySearchDto.lotteryId != null && lotterySearchDto.lotteryId.length() != 0 */
    and lottery.lottery_id = /* lotterySearchDto.lotteryId */'1'
   /*%end*/

  