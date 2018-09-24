package jp.co.sparkworks.restaurant.api.dto;

import lombok.Data;

@Data
public class LotteryApiDto {
	Long lotteryId;
	String lotteryDetail;
	String lotteryTitle;
	String lotteryImageUrl;
	String endDatetime;
	String announcementDatetime;
	int count;

}
