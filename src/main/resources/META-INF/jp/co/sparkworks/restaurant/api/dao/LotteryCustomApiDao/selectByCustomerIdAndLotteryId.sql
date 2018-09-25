SELECT
    /*%expand*/*
FROM
    lottery_application
WHERE
    is_actived = 1
AND customer_id=/*customerId*/123
AND lottery_id=/*lotteryId*/123