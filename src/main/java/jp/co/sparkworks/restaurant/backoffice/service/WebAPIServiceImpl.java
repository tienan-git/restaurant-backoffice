package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.co.opentone.arapp.backoffice.db.dao.YutaponHistoryDao;
import jp.co.opentone.arapp.backoffice.db.entity.YutaponHistory;
import jp.co.sparkworks.restaurant.backoffice.dao.YutaponHistoryCustomDao;
import jp.co.sparkworks.restaurant.backoffice.dto.Yutapon;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebAPIServiceImpl implements WebAPIService {
	
	@Autowired
	YutaponHistoryDao yutaponHistoryDao;
	
	@Autowired
	YutaponHistoryCustomDao yutaponHistoryCustomDao;

	@Override
	@Transactional
	public void synchronization(String deviceId, List<Yutapon> yutaponList) {

		// まず、該当端末のゆたぽん捕獲情報をすべて削除する
		 List<YutaponHistory> yutaponHistoryExistList=yutaponHistoryCustomDao.selectByDeviceId(deviceId);
		 if(!CollectionUtils.isEmpty(yutaponHistoryExistList)) {
			 yutaponHistoryDao.delete(yutaponHistoryExistList);
		 }
		

		// あと、最新データを新規作成する
		List<YutaponHistory> yutaponHistoryList = new ArrayList<YutaponHistory>();

		for (Yutapon yutapon : yutaponList) {
			YutaponHistory yutaponHistory = new YutaponHistory();
			yutaponHistory.setDeviceId(deviceId);
			yutaponHistory.setYutaponType(yutapon.getYutaponType());
			yutaponHistory.setSpotName(yutapon.getSpotName());
			yutaponHistory.setDateTime(yutapon.getDateTime());
			yutaponHistory.setStatus(Integer.parseInt(yutapon.getStatus()));

			yutaponHistoryList.add(yutaponHistory);

		}
		yutaponHistoryDao.insert(yutaponHistoryList);
	}

}
