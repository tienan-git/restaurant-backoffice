package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.Yutapon;

public interface WebAPIService {

	void synchronization(String deviceId, List<Yutapon> yutaponList);
}
