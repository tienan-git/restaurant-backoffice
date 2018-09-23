package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jp.co.opentone.arapp.backoffice.db.dao.YutaponHistoryDao;
import jp.co.opentone.arapp.backoffice.db.entity.YutaponHistory;
import jp.co.sparkworks.restaurant.backoffice.dao.YutaponHistoryCustomDao;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponSearchDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YutaponServiceImpl implements YutaponService {

    @Autowired
    YutaponHistoryDao yutaponHistoryDao;

    @Autowired
    YutaponHistoryCustomDao yutaponHistoryCustomDao;

    @Value("${search.limit}")
    private int searchLimit;

    @Override
    public List<YutaponDto> searchAll(YutaponSearchDto yutaponSearchDto) {

        SelectOptions selectOptions = SelectOptions.get().offset(0).limit(searchLimit).count();
        List<YutaponHistory> yutaponHistoryList = yutaponHistoryCustomDao.selectByCriteria(yutaponSearchDto, selectOptions);

        List<YutaponDto> yutaponDaoList = new ArrayList<YutaponDto>();

        for (YutaponHistory yutaponHistory : yutaponHistoryList) {

            YutaponDto yutaponDto = new YutaponDto();
            yutaponDto.setId(yutaponHistory.getYutaponHistoryId().toString());
            yutaponDto.setDeviceId(yutaponHistory.getDeviceId());
            yutaponDto.setYutaponType(yutaponHistory.getYutaponType());
            yutaponDto.setSpotName(yutaponHistory.getSpotName());
            yutaponDto.setDateTime(yutaponHistory.getDateTime());
            yutaponDto.setStatus(yutaponHistory.getStatus().toString());

            yutaponDaoList.add(yutaponDto);

        }
        return yutaponDaoList;
    }
}