package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;

public interface LotteryService {

	List<LotteryDto> search();

	LotteryDto create(LotteryDto LotteryDto);

	LotteryDto getById(Long LotteryId);

	void update(LotteryDto LotteryDto);

	void delete(Long LotteryId);

}
