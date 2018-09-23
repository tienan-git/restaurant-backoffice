package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.LotteryEventDto;

public interface LotteryEventService {

	List<LotteryEventDto> search();

	LotteryEventDto create(LotteryEventDto lotteryEventDto);

	LotteryEventDto getById(Long lotteryEventId);

	void update(LotteryEventDto lotteryEventDto);

	void delete(Long lotteryEventId);

}
