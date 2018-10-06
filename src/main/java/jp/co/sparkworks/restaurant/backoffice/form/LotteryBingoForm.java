package jp.co.sparkworks.restaurant.backoffice.form;

import java.util.List;

import lombok.Data;

@Data
public class LotteryBingoForm {

	Long lotteryId;
	List<Long> ids;
}
