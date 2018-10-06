package jp.co.sparkworks.restaurant.api.dto;

import lombok.Data;

@Data
public class LotteryApiDto {
	Long lotteryId;
	String lotteryTitle;
	String lotteryDetail;
	String lotteryImageUrl;
	int count;
	String lotteryApplicationStatus;
	String lotteryApplicationStatusName;
	String endDatetime;
	String announcementDatetime;

}
