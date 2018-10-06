package jp.co.sparkworks.restaurant.api.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.api.dto.LotteryApplicationApiDto;
import lombok.Data;

@Data
public class GetLotteriesHistoriesRes extends BaseRes {

	List<LotteryApplicationApiDto> lotteryHistories;

}
