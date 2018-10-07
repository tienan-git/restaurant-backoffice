package jp.co.sparkworks.restaurant.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sparkworks.restaurant.api.controller.param.BaseRes;
import jp.co.sparkworks.restaurant.api.controller.param.GetLotteriesHistoriesRes;
import jp.co.sparkworks.restaurant.api.controller.param.GetLotteriesRes;
import jp.co.sparkworks.restaurant.api.controller.param.GetRestaurantsDeviceIdRes;
import jp.co.sparkworks.restaurant.api.controller.param.PostFeedbacksReq;
import jp.co.sparkworks.restaurant.api.controller.param.SynchronizationReq;
import jp.co.sparkworks.restaurant.api.dto.CouponAndRestaurantApiDto;
import jp.co.sparkworks.restaurant.api.dto.FeedbackApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApplicationApiDto;
import jp.co.sparkworks.restaurant.api.service.WebAPIService;
import jp.co.sparkworks.restaurant.api.util.MDCUtil;
import jp.co.sparkworks.restaurant.backoffice.controller.constants.ResultCodeConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class WebApiController {

    @Autowired
    WebAPIService webAPIService;

    // １、同期
    // POST /synchronizations
    @PostMapping("/synchronizations")
    public BaseRes postSynchronization(@RequestBody SynchronizationReq req) {
        String deviceId = MDCUtil.getDeviceId();
        webAPIService.postSynchronization(deviceId, req.getNickName());

        return BaseRes.SUCCESS;
    }

    // ２、店一覧
    // GET /restaurants
    @GetMapping("/restaurants")
    public GetRestaurantsDeviceIdRes getRestaurantsDeviceId() {

        List<CouponAndRestaurantApiDto> restaurantDtoList = webAPIService.getRestaurants();

        GetRestaurantsDeviceIdRes res = new GetRestaurantsDeviceIdRes();
        res.setCode(ResultCodeConstants.I000);
        res.setRestaurants(restaurantDtoList);
        return res;
    }

    // ３、クーポン追加
    // POST /coupons/{couponId}
    @PostMapping("/coupons/{couponId}")
    public BaseRes postCoupons(@PathVariable Long couponId) {
        String deviceId = MDCUtil.getDeviceId();
        webAPIService.postCoupons(deviceId, couponId);
        return BaseRes.SUCCESS;
    }

    // ４、クーポン削除
    // DELETE /coupons/{couponId}
    @DeleteMapping("/coupons/{couponId}")
    public BaseRes deleteCoupons(@PathVariable Long couponId) {
        String deviceId = MDCUtil.getDeviceId();
        webAPIService.postCoupons(deviceId, couponId);
        return BaseRes.SUCCESS;
    }

    // ５、今の抽選
    // GET /lotteries
    @GetMapping("/lotteries")
    public BaseRes getLotteries() {

        LotteryApiDto lotteryApiDto = webAPIService.getLotteries(MDCUtil.getDeviceId());

        GetLotteriesRes res = new GetLotteriesRes();
        res.setCode(ResultCodeConstants.I000);
        res.setMessage("取得しました");
        res.setLottery(lotteryApiDto);

        return res;
    }

    // ６、抽選応募
    // POST /lotteries/{lotteryId}
    @PostMapping("/lotteries/{lotteryId}")
    public BaseRes postLotteries(@PathVariable Long lotteryId) {
        String deviceId = MDCUtil.getDeviceId();
        webAPIService.postLotteries(deviceId, lotteryId);
        return getLotteries();
    }

    // ７、抽選履歴取得
    // GET /lotteries/histories
    @GetMapping("/lotteries/histories")
    public GetLotteriesHistoriesRes getLotteriesHistories() {
        String deviceId = MDCUtil.getDeviceId();
        List<LotteryApplicationApiDto> lotteryApplicationApiDtoList = webAPIService.getLotteriesHistories(deviceId);

        GetLotteriesHistoriesRes res = new GetLotteriesHistoriesRes();
        res.setCode(ResultCodeConstants.I000);

        res.setLotteryHistories(lotteryApplicationApiDtoList);

        return res;
    }

    // ８、フィードバック
    // POST /feedbacks
    @PostMapping("/feedbacks")
    public BaseRes postFeedbacks(@RequestBody PostFeedbacksReq req) {
        String deviceId = MDCUtil.getDeviceId();
        FeedbackApiDto feedbackDto = new FeedbackApiDto();
        feedbackDto.setType(req.getType());
        feedbackDto.setDetail(req.getDetail());

        webAPIService.postFeedbacks(deviceId, feedbackDto);

        return BaseRes.SUCCESS;
    }

}
