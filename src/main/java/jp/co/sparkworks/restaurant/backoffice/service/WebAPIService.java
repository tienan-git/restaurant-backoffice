package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;

public interface WebAPIService {

    List<CouponDto> synchronization(String deviceId, String nickName);
}
