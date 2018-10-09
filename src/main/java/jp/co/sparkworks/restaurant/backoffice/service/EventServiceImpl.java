package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.db.dao.EventDao;

import jp.co.sparkworks.restaurant.backoffice.dto.EventDto;
import jp.co.sparkworks.restaurant.backoffice.dto.EventSearchDto;

@Service
public class EventServiceImpl implements EventService {


	@Autowired
	EventDao eventDao;

	@Transactional
	@Override
	public List<EventDto> searchAll(EventSearchDto eventSearchDto) {

//		List<EventDto> eventList = eventCustomDao.selectByCriteria(eventSearchDto);
//
		List<EventDto> EventDtoList = new ArrayList<EventDto>();
//
//		for (EventWithCount event : eventList) {
//
//			EventDto eventDto = new EventDto();
//			eventDto.setEventId(event.getEventId());
//			eventDto.setEventDetail(event.getEventDetail());
//			eventDto.setEventTitle(event.getEventTitle());
//			eventDto.setEventImageUrl(event.getEventImageUrl());
//			eventDto.setStartDatetime(event.getStartDatetime());
//			eventDto.setEndDatetime(event.getEndDatetime());
//			eventDto.setAnnouncementDatetime(event.getAnnouncementDatetime());
//			eventDto.setCouponId(event.getCouponId());
//			eventDto.setCount(event.getCount());
//
//			EventDtoList.add(eventDto);
//
//		}

		return EventDtoList;
	}

}
