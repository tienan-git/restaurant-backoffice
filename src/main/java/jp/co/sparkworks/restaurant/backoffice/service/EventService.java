package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.EventDto;
import jp.co.sparkworks.restaurant.backoffice.dto.EventSearchDto;

public interface EventService {

	List<EventDto> searchAll(EventSearchDto eventSearchDto);

}
