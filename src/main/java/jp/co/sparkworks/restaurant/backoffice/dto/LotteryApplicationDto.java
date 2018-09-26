package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LotteryApplicationDto {
 
    Long lotteryApplicationId; 
    Long lotteryId; 
    Long customerId; 
    String lotteryApplicationStatus;  
    LocalDateTime applyDatetime; 
    String validityFlag; 
    int count; 
    String deviceId; 
    String nickName;
}
