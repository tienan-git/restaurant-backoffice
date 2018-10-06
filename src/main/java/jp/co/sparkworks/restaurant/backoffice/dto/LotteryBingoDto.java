package jp.co.sparkworks.restaurant.backoffice.dto;

import java.util.List;

import lombok.Data;

@Data
public class LotteryBingoDto {

	Long lotteryId;
	List<Long> ids;
}
