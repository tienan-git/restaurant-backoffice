package jp.co.sparkworks.restaurant.api.dto;

import lombok.Data;

@Data
public class LotteryApplicationApiDto {
	String lotteryTitle;
	String lotteryDetail;
	String lotteryApplicationStatus;
	String applyDatetime;
}
