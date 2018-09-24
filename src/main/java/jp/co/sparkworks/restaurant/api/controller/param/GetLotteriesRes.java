package jp.co.sparkworks.restaurant.api.controller.param;

import lombok.Data;

@Data
public class GetLotteriesRes extends BaseRes {

	long lotteryId;
	String lotteryTitle;
	String lotteryDetail;
	String lotteryImageUrl;
	String endDatetime;
	String announcementDatetime;
	int count;

}
