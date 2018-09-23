package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sparkworks.restaurant.backoffice.controller.constants.ResultCodeConstants;
import jp.co.sparkworks.restaurant.backoffice.controller.param.SynchronizationReq;
import jp.co.sparkworks.restaurant.backoffice.controller.param.SynchronizationRes;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.service.WebAPIService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WebApiController {

    @Autowired
    WebAPIService webAPIService;

    // １、同期
    // POST /synchronizations/{deviceId}
    @PostMapping("/synchronization/{deviceId}")
    public SynchronizationRes synchronization(@PathVariable String deviceId, @RequestBody SynchronizationReq req) {

        List<CouponDto> couponDtoList = webAPIService.synchronization(deviceId, req.getNickName());

        SynchronizationRes res = new SynchronizationRes();
        res.setCode(ResultCodeConstants.I000);
        res.setMessage("取得しました");
        res.setCouponDtoList(couponDtoList);
        return res;

    }

    // ２、店一覧
    // GET /restaurants/{deviceId}
    //
    // ３、クーポン追加
    // POST /coupons/{deviceId}/{couponId}
    //
    // ４、クーポン削除
    // DELETE /coupons/{deviceId}/{couponId}
    //
    // ５、今の抽選
    // GET /lotteries/{deviceId}
    //
    // ６、抽選応募
    // POST /lotteries/{lotteryId}/{deviceId}
    //
    // ７、抽選履歴取得
    // GET /lotteries/histories/{deviceId}
    //
    // ８、フィードバック
    // POST /feedbacks/{deviceId}
}
