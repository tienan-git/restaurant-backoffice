package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.LotteryCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.LotteryDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Lottery;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplicationWithCustomer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithCount;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

@Service
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	LotteryCustomDao lotteryCustomDao;

	@Autowired
	LotteryDao lotteryDao;

	@Transactional
	@Override
	public List<LotteryDto> searchAll(LotterySearchDto lotterySearchDto) {

		List<LotteryWithCount> lotteryList = lotteryCustomDao.selectByCriteria(lotterySearchDto);

		List<LotteryDto> LotteryDtoList = new ArrayList<LotteryDto>();

		for (LotteryWithCount lottery : lotteryList) {

			LotteryDto lotteryDto = new LotteryDto();
			lotteryDto.setLotteryId(lottery.getLotteryId());
			lotteryDto.setLotteryDetail(lottery.getLotteryDetail());
			lotteryDto.setLotteryTitle(lottery.getLotteryTitle());
			lotteryDto.setLotteryImageUrl(lottery.getLotteryImageUrl());
			lotteryDto.setStartDatetime(lottery.getStartDatetime());
			lotteryDto.setEndDatetime(lottery.getEndDatetime());
			lotteryDto.setAnnouncementDatetime(lottery.getAnnouncementDatetime());
			lotteryDto.setCouponId(lottery.getCouponId());
			lotteryDto.setCount(lottery.getCount());

			LotteryDtoList.add(lotteryDto);

		}

		return LotteryDtoList;
	}

	@Transactional
	@Override
	public LotteryDto create(LotteryDto LotteryDto) {

		// DTO->Entity
		Lottery Lottery = new Lottery();

		Lottery.setLotteryDetail(LotteryDto.getLotteryDetail());
		Lottery.setLotteryTitle(LotteryDto.getLotteryTitle());
		Lottery.setLotteryImageUrl(LotteryDto.getLotteryImageUrl());
		Lottery.setStartDatetime(LotteryDto.getStartDatetime());
		Lottery.setEndDatetime(LotteryDto.getEndDatetime());
		Lottery.setAnnouncementDatetime(LotteryDto.getAnnouncementDatetime());
		Lottery.setCouponId(LotteryDto.getCouponId());

		// DB access
		lotteryDao.insert(Lottery);

		// Entity->DTO
		LotteryDto newLotteryDto = new LotteryDto();

		LotteryDto.setLotteryId(Lottery.getLotteryId());
		LotteryDto.setLotteryDetail(Lottery.getLotteryDetail());
		LotteryDto.setLotteryTitle(Lottery.getLotteryTitle());
		LotteryDto.setLotteryImageUrl(Lottery.getLotteryImageUrl());
		LotteryDto.setStartDatetime(Lottery.getStartDatetime());
		LotteryDto.setEndDatetime(Lottery.getEndDatetime());
		LotteryDto.setAnnouncementDatetime(Lottery.getAnnouncementDatetime());
		LotteryDto.setCouponId(Lottery.getCouponId());

		return newLotteryDto;
	}

	@Transactional
	@Override
	public LotteryDto getById(Long lotteryId) {

		Lottery lottery = lotteryDao.selectById(lotteryId);
		if (lottery == null) {
			throw new BusinessException(ErrorCodeConstant.E10012);
		}
		LotteryDto lotteryDto = new LotteryDto();

		lotteryDto.setLotteryId(lottery.getLotteryId());
		lotteryDto.setLotteryDetail(lottery.getLotteryDetail());
		lotteryDto.setLotteryTitle(lottery.getLotteryTitle());
		lotteryDto.setLotteryImageUrl(lottery.getLotteryImageUrl());
		lotteryDto.setStartDatetime(lottery.getStartDatetime());
		lotteryDto.setEndDatetime(lottery.getEndDatetime());
		lotteryDto.setAnnouncementDatetime(lottery.getAnnouncementDatetime());
		lotteryDto.setCouponId(lottery.getCouponId());
		// lotteryDto.setCount(lottery.getCount());

		return lotteryDto;

	}

	@Transactional
	@Override
	public void update(LotteryDto LotteryDto) {

		Lottery Lottery = lotteryDao.selectById(LotteryDto.getLotteryId());

		LotteryDto.setLotteryDetail(Lottery.getLotteryDetail());
		LotteryDto.setLotteryTitle(Lottery.getLotteryTitle());
		LotteryDto.setLotteryImageUrl(Lottery.getLotteryImageUrl());
		LotteryDto.setStartDatetime(Lottery.getStartDatetime());
		LotteryDto.setEndDatetime(Lottery.getEndDatetime());
		LotteryDto.setAnnouncementDatetime(Lottery.getAnnouncementDatetime());
		LotteryDto.setCouponId(Lottery.getCouponId());

		lotteryDao.update(Lottery);

	}

	@Transactional
	@Override
	public void delete(Long LotteryId) {

		Lottery lottery = lotteryDao.selectById(LotteryId);
		if (lottery == null) {
			throw new BusinessException(ErrorCodeConstant.E10012);
		}
		lotteryDao.delete(lottery);

	}

	@Override
	public List<LotteryApplicationDto> getLotteryApplicationById(Long lotteryId) {

		List<LotteryApplicationWithCustomer> lotteryList = lotteryCustomDao.selectApplicationByCriteria(lotteryId);

		List<LotteryApplicationDto> lotteryApplicationDtoList = new ArrayList<LotteryApplicationDto>();

		for (LotteryApplicationWithCustomer lottery : lotteryList) {

			LotteryApplicationDto lotteryApplicationDto = new LotteryApplicationDto();
			lotteryApplicationDto.setDeviceId(lottery.getDeviceId());
			lotteryApplicationDto.setCustomerId(lottery.getCustomerId());
			lotteryApplicationDto.setLotteryApplicationId(lottery.getLotteryApplicationId());
			lotteryApplicationDto.setLotteryApplicationStatus(lottery.getLotteryApplicationStatus());
			lotteryApplicationDto.setNickName(lottery.getNickName());
			lotteryApplicationDto.setValidityFlag(lottery.getValidityFlag());
			lotteryApplicationDto.setApplyDatetime(lottery.getApplyDatetime());

			lotteryApplicationDtoList.add(lotteryApplicationDto);

			// TODO Auto-generated method stub
			
		}
		return lotteryApplicationDtoList;
	}
}
