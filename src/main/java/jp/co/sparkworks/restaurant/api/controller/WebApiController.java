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
import jp.co.sparkworks.restaurant.api.controller.param.SynchronizationRes;
import jp.co.sparkworks.restaurant.api.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.api.service.WebAPIService;
import jp.co.sparkworks.restaurant.backoffice.controller.constants.ResultCodeConstants;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class WebApiController {

	@Autowired
	WebAPIService webAPIService;

	// １、同期
	// POST /synchronizations/{deviceId}
	@PostMapping("/synchronization/{deviceId}")
	public SynchronizationRes postSynchronization(@PathVariable String deviceId, @RequestBody SynchronizationReq req) {

		List<CouponDto> couponDtoList = webAPIService.synchronization(deviceId, req.getNickName());

		SynchronizationRes res = new SynchronizationRes();
		res.setCode(ResultCodeConstants.I000);
		res.setMessage("取得しました");
		res.setCouponDtoList(couponDtoList);
		return res;

	}

	// ２、店一覧
	// GET /restaurants/{deviceId}
	@GetMapping("/restaurants/{deviceId}")
	public GetRestaurantsDeviceIdRes getRestaurantsDeviceId(@PathVariable String deviceId) {

		List<RestaurantDto> restaurantDtoList = webAPIService.getRestaurants();

		GetRestaurantsDeviceIdRes res = new GetRestaurantsDeviceIdRes();
		res.setCode(ResultCodeConstants.I000);
		res.setMessage("取得しました");
		res.setRestaurantDtoList(restaurantDtoList);
		return res;

	}

	// ３、クーポン追加
	// POST /coupons/{deviceId}/{couponId}
	@PostMapping("/coupons/{deviceId}/{couponId}")
	public BaseRes postCoupons(@PathVariable String deviceId, @PathVariable String couponId) {

		webAPIService.postCoupons(deviceId, couponId);

		return BaseRes.SUCCESS;

	}
	// ４、クーポン削除
	// DELETE /coupons/{deviceId}/{couponId}

	@DeleteMapping("/coupons/{deviceId}/{couponId}")
	public BaseRes deleteCoupons(@PathVariable String deviceId, @PathVariable String couponId) {

		webAPIService.postCoupons(deviceId, couponId);

		return BaseRes.SUCCESS;

	}

	// ５、今の抽選
	// GET /lotteries/{deviceId}
	@GetMapping("/lotteries/{deviceId}")
	public GetLotteriesRes getLotteries(@PathVariable String deviceId) {

		webAPIService.getLotteries();

		GetLotteriesRes res=new GetLotteriesRes();
		return res;
	}

	// ６、抽選応募
	// POST /lotteries/{lotteryId}/{deviceId}
	@PostMapping("/lotteries/{lotteryId}/{deviceId}")
	public BaseRes postLotteries(@PathVariable String deviceId, @PathVariable String lotteryId) {
		webAPIService.postLotteries(deviceId, lotteryId);
		return BaseRes.SUCCESS;
	}

	// ７、抽選履歴取得
	// GET /lotteries/histories/{deviceId}
	@GetMapping("/lotteries/histories/{deviceId}")
	public GetLotteriesHistoriesRes getLotteriesHistories(@PathVariable String deviceId) {

		webAPIService.getLotteriesHistories(deviceId);
		
		GetLotteriesHistoriesRes res=new GetLotteriesHistoriesRes();
		
		return res;
	}

	// ８、フィードバック
	// POST /feedbacks/{deviceId}
	@PostMapping("/feedbacks/{deviceId}")
	public BaseRes postFeedbacks(@PathVariable String deviceId, @RequestBody PostFeedbacksReq req) {

		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setType(req.getType());
		feedbackDto.setDetail(req.getDetail());

		webAPIService.postFeedbacks(deviceId, feedbackDto);

		return BaseRes.SUCCESS;

	}

}
