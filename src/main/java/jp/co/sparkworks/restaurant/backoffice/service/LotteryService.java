package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;

public interface LotteryService {

	List<LotteryDto> searchAll(LotterySearchDto lotterySearchDto);

	LotteryDto create(LotteryDto lotteryDto);

	LotteryDto getById(Long lotteryId);

	List<LotteryApplicationDto> getLotteryApplicationById(Long lotteryId);

	void update(LotteryDto lotteryDto);

	void delete(Long lotteryId);

}
