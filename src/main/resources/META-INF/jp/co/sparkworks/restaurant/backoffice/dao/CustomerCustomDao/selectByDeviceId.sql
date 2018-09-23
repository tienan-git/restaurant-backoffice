SELECT
    /*%expand*/*
FROM
    customer
WHERE
    is_actived = 1
    and device_id = /* deviceId */'xxxxx'
