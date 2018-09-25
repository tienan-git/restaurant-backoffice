SELECT
    feedback.*
FROM
    feedback
	join customer on feedback.customer_id=customer.customer_id

WHERE
    feedback.is_actived = 1
   
	/*%if  feedbackSearchDto.deviceId != null && feedbackSearchDto.deviceId.length() != 0 */
    and customer.device_id = /* feedbackSearchDto.deviceId */'xxxxx'
   /*%end*/

   /*%if  feedbackSearchDto.type != null && feedbackSearchDto.type.length() != 0 */
    and feedback.type = /* feedbackSearchDto.type */'%X%'
   /*%end*/
    
    /*%if  feedbackSearchDto.treatmentStatus != null && feedbackSearchDto.treatmentStatus.length() != 0 */
    and feedback.treatment_Status =/* feedbackSearchDto.treatmentStatus */'%X%' 
   /*%end*/
