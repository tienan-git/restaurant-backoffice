package jp.co.sparkworks.restaurant.backoffice.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LotteryEventDto {
	Long lotteryEventId;
	String lotteryDetail;
	String lotteryTitle;
	String lotteryImageUrl;
	LocalDateTime startDatetime;
	LocalDateTime endDatetime;
	LocalDateTime announcementDatetime;
	Long couponId;

}
