package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

public interface WebAPIService {

    // １、同期
    List<CouponDto> synchronization(String deviceId, String nickName);

    // ２、店一覧
    List<RestaurantDto> getRestaurants();

    // ３、クーポン追加
    void postCoupons(String deviceId, String couponId);

    // ４、クーポン削除
    void deleteCoupons(String deviceId, String couponId);

    // ５、今の抽選
    LotteryDto getLotteries();

    // ６、抽選応募
    void postLotteries(String deviceId, String couponId);

    // ７、抽選履歴取得
    List getLotteriesHistories(String deviceId);

    // ８、フィードバック
    void postFeedbacks(String deviceId, FeedbackDto feedbackDto);
}
