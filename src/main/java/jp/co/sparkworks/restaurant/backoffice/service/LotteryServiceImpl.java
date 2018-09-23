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
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    LotteryCustomDao LotteryCustomDao;

    @Autowired
    LotteryDao LotteryDao;

    @Transactional
    @Override
    public List<LotteryDto> search() {

        List<Lottery> LotteryList = LotteryCustomDao.selectAll();

        List<LotteryDto> LotteryDtoList = new ArrayList<LotteryDto>();

        for (Lottery Lottery : LotteryList) {

            LotteryDto LotteryDto = new LotteryDto();
            
            LotteryDto.setLotteryId(Lottery.getLotteryId());
            LotteryDto.setLotteryDetail(Lottery.getLotteryDetail());
            LotteryDto.setLotteryTitle(Lottery.getLotteryTitle());
            LotteryDto.setLotteryImageUrl(Lottery.getLotteryImageUrl());
            LotteryDto.setStartDatetime(Lottery.getStartDatetime());
            LotteryDto.setEndDatetime(Lottery.getEndDatetime());
            LotteryDto.setAnnouncementDatetime(Lottery.getAnnouncementDatetime());
            LotteryDto.setCouponId(Lottery.getCouponId());
            
            LotteryDtoList.add(LotteryDto);
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
        LotteryDao.insert(Lottery);

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
    public LotteryDto getById(Long LotteryId) {

        Lottery Lottery = LotteryDao.selectById(LotteryId);
        if (Lottery == null) {
            throw new BusinessException(ErrorCodeConstant.E10012);
        }
        LotteryDto LotteryDto = new LotteryDto();

        LotteryDto.setLotteryId(Lottery.getLotteryId());
        LotteryDto.setLotteryDetail(Lottery.getLotteryDetail());
        LotteryDto.setLotteryTitle(Lottery.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(Lottery.getLotteryImageUrl());
        LotteryDto.setStartDatetime(Lottery.getStartDatetime());
        LotteryDto.setEndDatetime(Lottery.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(Lottery.getAnnouncementDatetime());
        LotteryDto.setCouponId(Lottery.getCouponId());

        return LotteryDto;

    }

    @Transactional
    @Override
    public void update(LotteryDto LotteryDto) {

        Lottery Lottery = LotteryDao.selectById(LotteryDto.getLotteryId());

        LotteryDto.setLotteryDetail(Lottery.getLotteryDetail());
        LotteryDto.setLotteryTitle(Lottery.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(Lottery.getLotteryImageUrl());
        LotteryDto.setStartDatetime(Lottery.getStartDatetime());
        LotteryDto.setEndDatetime(Lottery.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(Lottery.getAnnouncementDatetime());
        LotteryDto.setCouponId(Lottery.getCouponId());

        LotteryDao.update(Lottery);

    }

    @Transactional
    @Override
    public void delete(Long LotteryId) {

        Lottery Lottery = LotteryDao.selectById(LotteryId);
        if (Lottery == null) {
            throw new BusinessException(ErrorCodeConstant.E10012);
        }
        LotteryDao.delete(Lottery);

    }



}
