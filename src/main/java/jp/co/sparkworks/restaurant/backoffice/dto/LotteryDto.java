package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDateTime;

import jp.co.sparkworks.restaurant.backoffice.util.DateUtil;
import lombok.Data;

@Data
public class LotteryDto {
	Long lotteryId;
	String lotteryDetail;
	String lotteryTitle;
	String lotteryImageUrl;
	LocalDateTime startDatetime;
	LocalDateTime endDatetime;
	LocalDateTime announcementDatetime;
	Long couponId;
	int count;
	
	public String getStartDatetimeFormat() {
		if(startDatetime == null) {
			return null;
		}
		return DateUtil.localDateTimeFormat(startDatetime);	
	}
	
	public String getEndDatetimeFormat() {
		if(endDatetime == null) {
			return null;
		}
		return DateUtil.localDateTimeFormat(endDatetime);	
	}
	
	public String getAnnouncementDatetimeFormat() {
		if(announcementDatetime == null) {
			return null;
		}
		return DateUtil.localDateTimeFormat(announcementDatetime);	
	}
	
}
