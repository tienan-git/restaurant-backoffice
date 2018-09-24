package jp.co.sparkworks.restaurant.api.service;

import java.util.List;

import jp.co.sparkworks.restaurant.api.dto.CouponAndRestaurantApiDto;
import jp.co.sparkworks.restaurant.api.dto.FeedbackApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApiDto;

public interface WebAPIService {

	// １、同期
	List<CouponAndRestaurantApiDto> postSynchronization(String deviceId, String nickName);

	// ２、店一覧
	List<CouponAndRestaurantApiDto> getRestaurants();

	// ３、クーポン追加
	void postCoupons(String deviceId, String couponId);

	// ４、クーポン削除
	void deleteCoupons(String deviceId, String couponId);

	// ５、今の抽選
	LotteryApiDto getLotteries();

	// ６、抽選応募
	void postLotteries(String deviceId, String couponId);

	// ７、抽選履歴取得
	List getLotteriesHistories(String deviceId);

	// ８、フィードバック
	void postFeedbacks(String deviceId, FeedbackApiDto feedbackDto);
}
