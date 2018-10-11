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
	LocalDateTime displayStartDatetime;
   LocalDateTime displayEndDatetime;
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
	public String getDisplayStartDatetimeFormat() {
		
		if(displayStartDatetime == null) {
			return null;
		}
		return DateUtil.localDateTimeFormat(displayStartDatetime);	
	}
	
	public String getDisplayEndDatetimeFormat() {
		if(displayEndDatetime == null) {
			return null;
		}
		return DateUtil.localDateTimeFormat(displayEndDatetime);	
	}
	
}
