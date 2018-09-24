SELECT
    /*%expand*/*
FROM
    coupon_hold
WHERE
    is_actived = 1
AND customer_id=/*customerId*/'123'
AND coupon_id=/*couponId*/'123'