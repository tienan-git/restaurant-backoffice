package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;

public interface LotteryService {

	List<LotteryDto> searchAll(LotterySearchDto lotterySearchDto);

	LotteryDto create(LotteryDto LotteryDto);

	LotteryDto getById(Long LotteryId);

	void update(LotteryDto LotteryDto);

	void delete(Long LotteryId);

}
