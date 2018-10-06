package jp.co.sparkworks.restaurant.backoffice.form;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LotteryInputForm {
	Long lotteryId;
	String lotteryDetail;
	String lotteryTitle;
	String lotteryImageUrl;
	String startDatetime;
	String endDatetime;
	String announcementDatetime;
	Long couponId;
}
