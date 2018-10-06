SELECT
	/*%expand*/*
FROM
	lottery_application
WHERE
	is_actived = 1
	/*%if ids != null*/
	and customer_id in /*ids*/('3','2','1')
	/*%end*/
	/*%if  lotteryId != null */
	and lottery_id = /* lotteryId */'1' 
	/*%end*/