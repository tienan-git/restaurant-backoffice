package jp.co.sparkworks.restaurant.backoffice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.LotteryEventCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.LotteryEventDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryEvent;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryEventDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

@Service
public class LotteryEventServiceImpl implements LotteryEventService {

    @Autowired
    LotteryEventCustomDao lotteryEventCustomDao;

    @Autowired
    LotteryEventDao lotteryEventDao;

    @Transactional
    @Override
    public List<LotteryEventDto> search() {

        List<LotteryEvent> lotteryEventList = lotteryEventCustomDao.selectAll();

        List<LotteryEventDto> lotteryEventDtoList = new ArrayList<LotteryEventDto>();

        for (LotteryEvent lotteryEvent : lotteryEventList) {

            LotteryEventDto lotteryEventDto = new LotteryEventDto();
            
            lotteryEventDto.setLotteryEventId(lotteryEvent.getLotteryEventId());
            lotteryEventDto.setLotteryDetail(lotteryEvent.getLotteryDetail());
            lotteryEventDto.setLotteryTitle(lotteryEvent.getLotteryTitle());
            lotteryEventDto.setLotteryImageUrl(lotteryEvent.getLotteryImageUrl());
            lotteryEventDto.setStartDatetime(lotteryEvent.getStartDatetime());
            lotteryEventDto.setEndDatetime(lotteryEvent.getEndDatetime());
            lotteryEventDto.setAnnouncementDatetime(lotteryEvent.getAnnouncementDatetime());
            lotteryEventDto.setCouponId(lotteryEvent.getCouponId());
            
            lotteryEventDtoList.add(lotteryEventDto);
        }

        return lotteryEventDtoList;
    }

    @Transactional
    @Override
    public LotteryEventDto create(LotteryEventDto lotteryEventDto) {

        // DTO->Entity
        LotteryEvent lotteryEvent = new LotteryEvent();
        
        lotteryEvent.setLotteryDetail(lotteryEventDto.getLotteryDetail());
        lotteryEvent.setLotteryTitle(lotteryEventDto.getLotteryTitle());
        lotteryEvent.setLotteryImageUrl(lotteryEventDto.getLotteryImageUrl());
        lotteryEvent.setStartDatetime(lotteryEventDto.getStartDatetime());
        lotteryEvent.setEndDatetime(lotteryEventDto.getEndDatetime());
        lotteryEvent.setAnnouncementDatetime(lotteryEventDto.getAnnouncementDatetime());
        lotteryEvent.setCouponId(lotteryEventDto.getCouponId());

        // DB access
        lotteryEventDao.insert(lotteryEvent);

        // Entity->DTO
        LotteryEventDto newLotteryEventDto = new LotteryEventDto();

        lotteryEventDto.setLotteryEventId(lotteryEvent.getLotteryEventId());
        lotteryEventDto.setLotteryDetail(lotteryEvent.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEvent.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEvent.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEvent.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEvent.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEvent.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEvent.getCouponId());

        return newLotteryEventDto;
    }

    @Transactional
    @Override
    public LotteryEventDto getById(Long lotteryEventId) {

        LotteryEvent lotteryEvent = lotteryEventDao.selectById(lotteryEventId);
        if (lotteryEvent == null) {
            throw new BusinessException(ErrorCodeConstant.E10012);
        }
        LotteryEventDto lotteryEventDto = new LotteryEventDto();

        lotteryEventDto.setLotteryEventId(lotteryEvent.getLotteryEventId());
        lotteryEventDto.setLotteryDetail(lotteryEvent.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEvent.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEvent.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEvent.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEvent.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEvent.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEvent.getCouponId());

        return lotteryEventDto;

    }

    @Transactional
    @Override
    public void update(LotteryEventDto lotteryEventDto) {

        LotteryEvent lotteryEvent = lotteryEventDao.selectById(lotteryEventDto.getLotteryEventId());

        lotteryEventDto.setLotteryDetail(lotteryEvent.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEvent.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEvent.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEvent.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEvent.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEvent.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEvent.getCouponId());

        lotteryEventDao.update(lotteryEvent);

    }

    @Transactional
    @Override
    public void delete(Long lotteryEventId) {

        LotteryEvent lotteryEvent = lotteryEventDao.selectById(lotteryEventId);
        if (lotteryEvent == null) {
            throw new BusinessException(ErrorCodeConstant.E10012);
        }
        lotteryEventDao.delete(lotteryEvent);

    }



}
