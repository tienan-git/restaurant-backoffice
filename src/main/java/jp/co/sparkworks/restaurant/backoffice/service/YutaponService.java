package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.Yutapon;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponSearchDto;

public interface YutaponService {

	List<YutaponDto> searchAll(YutaponSearchDto yutaponSearchDto);
}
