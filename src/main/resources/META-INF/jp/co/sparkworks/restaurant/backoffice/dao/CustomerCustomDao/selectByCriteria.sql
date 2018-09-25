	SELECT
    	    /*%expand*/*
    	FROM
    	    customer
    	WHERE
    	    is_actived = 1
    	   
    		/*%if  customerSearchDto.deviceId != null && customerSearchDto.deviceId.length() != 0 */
    	    		 and device_id = /* customerSearchDto.deviceId */'xxxxx'
    	   /*%end*/

    	   /*%if  customerSearchDto.nickName != null && customerSearchDto.nickName.length() != 0 */
    	    and nick_name like /* @infix(customerSearchDto.nickName) */'%X%' escape '$'
    	   /*%end*/
