package jp.co.sparkworks.restaurant.api.controller.param;

import jp.co.sparkworks.restaurant.api.dto.LotteryApiDto;
import lombok.Data;

@Data
public class GetLotteriesRes extends BaseRes {

	private LotteryApiDto lottery;

}
