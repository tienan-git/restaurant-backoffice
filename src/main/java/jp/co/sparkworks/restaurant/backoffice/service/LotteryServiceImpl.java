package jp.co.sparkworks.restaurant.backoffice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.LotteryApplicationCustomDao;
import jp.co.sparkworks.restaurant.backoffice.dao.LotteryCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CouponHoldDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.LotteryApplicationDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.LotteryDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Lottery;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplication;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplicationWithCustomer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithCount;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryBingoDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;
import jp.co.sparkworks.restaurant.backoffice.enums.LotteryApplicationStatus;
import jp.co.sparkworks.restaurant.backoffice.enums.LotteryStatus;
import jp.co.sparkworks.restaurant.exception.BusinessException;

@Service
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	LotteryCustomDao lotteryCustomDao;

	@Autowired
	LotteryDao lotteryDao;

	@Autowired
	CouponHoldDao couponHoldDao;

	@Autowired
	LotteryApplicationCustomDao lotteryApplicationCustomDao;

	@Autowired
	LotteryApplicationDao lotteryApplicationDao;

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
			lotteryDto.setDisplayStartDatetime(lottery.getDisplayStartDatetime());
			lotteryDto.setDisplayEndDatetime(lottery.getDisplayEndDatetime());
			// lotteryDto.setCouponId(lottery.getCouponId());
			lotteryDto.setCount(lottery.getCount());
			lotteryDto.setLotteryStatus(lottery.getLotteryStatus());

			LotteryDtoList.add(lotteryDto);

		}

		return LotteryDtoList;
	}

	@Transactional
	@Override
	public LotteryDto create(LotteryDto lotteryDto) {

		// DTO->Entity
		Lottery lottery = new Lottery();

		lottery.setLotteryDetail(lotteryDto.getLotteryDetail());
		lottery.setLotteryTitle(lotteryDto.getLotteryTitle());
		lottery.setLotteryImageUrl(lotteryDto.getLotteryImageUrl());
		lottery.setStartDatetime(lotteryDto.getStartDatetime());
		lottery.setEndDatetime(lotteryDto.getEndDatetime());
		lottery.setAnnouncementDatetime(lotteryDto.getAnnouncementDatetime());
		lottery.setDisplayStartDatetime(lotteryDto.getDisplayStartDatetime());
		lottery.setDisplayEndDatetime(lotteryDto.getDisplayEndDatetime());
		// lottery.setCouponId(lotteryDto.getCouponId());

		// DB access
		lotteryDao.insert(lottery);

		// Entity->DTO
		LotteryDto newLotteryDto = new LotteryDto();

		newLotteryDto.setLotteryId(lottery.getLotteryId());
		newLotteryDto.setLotteryDetail(lottery.getLotteryDetail());
		newLotteryDto.setLotteryTitle(lottery.getLotteryTitle());
		newLotteryDto.setLotteryImageUrl(lottery.getLotteryImageUrl());
		newLotteryDto.setStartDatetime(lottery.getStartDatetime());
		newLotteryDto.setEndDatetime(lottery.getEndDatetime());
		newLotteryDto.setAnnouncementDatetime(lottery.getAnnouncementDatetime());
		newLotteryDto.setDisplayStartDatetime(lottery.getDisplayStartDatetime());
		newLotteryDto.setDisplayEndDatetime(lottery.getDisplayEndDatetime());
		// newLotteryDto.setCouponId(lottery.getCouponId());

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
		lotteryDto.setDisplayStartDatetime(lottery.getDisplayStartDatetime());
		lotteryDto.setDisplayEndDatetime(lottery.getDisplayEndDatetime());
		lotteryDto.setLotteryStatus(lottery.getLotteryStatus());
		// lotteryDto.setCouponId(lottery.getCouponId());
		// lotteryDto.setCount(lottery.getCount());

		return lotteryDto;

	}

	@Transactional
	@Override
	public void update(LotteryDto lotteryDto) {

		Lottery lottery = lotteryDao.selectById(lotteryDto.getLotteryId());

		lottery.setLotteryDetail(lotteryDto.getLotteryDetail());
		lottery.setLotteryTitle(lotteryDto.getLotteryTitle());
		lottery.setLotteryImageUrl(lotteryDto.getLotteryImageUrl());
		lottery.setStartDatetime(lotteryDto.getStartDatetime());
		lottery.setEndDatetime(lotteryDto.getEndDatetime());
		lottery.setAnnouncementDatetime(lotteryDto.getAnnouncementDatetime());
		lottery.setDisplayStartDatetime(lotteryDto.getDisplayStartDatetime());
		lottery.setDisplayEndDatetime(lotteryDto.getDisplayEndDatetime());
		// lotteryDto.setCouponId(lottery.getCouponId());

		lotteryDao.update(lottery);

	}

	@Transactional
	@Override
	public void delete(Long lotteryId) {

		Lottery lottery = lotteryDao.selectById(lotteryId);
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

	@Override
	public void bingo(LotteryBingoDto lotteryBingoDto) {

		Lottery lottery = lotteryDao.selectById(lotteryBingoDto.getLotteryId());
		lottery.setLotteryStatus(LotteryStatus.DONE.getValue());
		lotteryDao.update(lottery);

		List<LotteryApplication> lotteryApplications = lotteryApplicationCustomDao
				.selectByLotteryId(lotteryBingoDto.getLotteryId());

		for (LotteryApplication lotteryApplication : lotteryApplications) {
			if (lotteryBingoDto.getIds().contains(lotteryApplication.getCustomerId())) {
				lotteryApplication.setLotteryApplicationStatus(LotteryApplicationStatus.BINGO.getValue());
			} else {
				lotteryApplication.setLotteryApplicationStatus(LotteryApplicationStatus.SORRY.getValue());
			}
			lotteryApplicationDao.update(lotteryApplication);
		}
	}


	
}
