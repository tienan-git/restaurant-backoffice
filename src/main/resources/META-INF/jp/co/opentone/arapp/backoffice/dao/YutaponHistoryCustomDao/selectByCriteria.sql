select
  /*%expand*/*
from
  yutapon_history

WHERE 
is_actived = 1

/*%if  yutaponSearchDto.dateTimeFrom != null && yutaponSearchDto.dateTimeFrom.length() != 0 */
	     and date_time >    /* yutaponSearchDto.dateTimeFrom */'2018-01-25'
/*%end*/

/*%if  yutaponSearchDto.dateTimeTo != null && yutaponSearchDto.dateTimeTo.length() != 0 */
and date_time <=  /* yutaponSearchDto.dateTimeTo+" 24:00:00" */'2018-01-25'
/*%end*/


/*%if  yutaponSearchDto.spotName != null && yutaponSearchDto.spotName.length() != 0 */
 and spot_name like /* @infix(yutaponSearchDto.spotName) */'%X%' escape '$'
/*%end*/

 /*%if  yutaponSearchDto.yutaponType != null && yutaponSearchDto.yutaponType.length() != 0 */
 and yutapon_type =  /* yutaponSearchDto.yutaponType */'red' 
/*%end*/
 
 /*%if  yutaponSearchDto.status != null && yutaponSearchDto.status.length() != 0 */
 and status =  /* yutaponSearchDto.status */'2' 
/*%end*/

 /*%if  yutaponSearchDto.deviceId != null && yutaponSearchDto.deviceId.length() != 0 */
 and device_id =  /* yutaponSearchDto.deviceId */'E493DE7A-200C-425C-A7DF-26E37D3AB466' 
/*%end*/

ORDER BY date_time DESC