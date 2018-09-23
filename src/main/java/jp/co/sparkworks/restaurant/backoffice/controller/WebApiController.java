package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sparkworks.restaurant.backoffice.controller.constants.ResultCodeConstants;
import jp.co.sparkworks.restaurant.backoffice.controller.param.BaseRes;
import jp.co.sparkworks.restaurant.backoffice.controller.param.GetRestaurantsDeviceIdRes;
import jp.co.sparkworks.restaurant.backoffice.controller.param.PostFeedbacksReq;
import jp.co.sparkworks.restaurant.backoffice.controller.param.SynchronizationReq;
import jp.co.sparkworks.restaurant.backoffice.controller.param.SynchronizationRes;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
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

<<<<<<< HEAD
	// ５、今の抽選
	// GET /lotteries/{deviceId}
	@GetMapping("/lotteries/{deviceId}")
	public BaseRes getCoupons(@PathVariable String deviceId) {
=======
    // ５、今の抽選
    // GET /lotteries/{deviceId}
    @GetMapping("/coupons/{deviceId}/{couponId}")
    public BaseRes getCoupons(@PathVariable String deviceId, @PathVariable String couponId) {
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git

		webAPIService.getCoupons();

		return BaseRes.SUCCESS;
	}

<<<<<<< HEAD
	// ６、抽選応募
	// POST /lotteries/{lotteryId}/{deviceId}
	@PostMapping("/lotteries/{lotteryId}/{deviceId}")
	public BaseRes postCouponsDeviceId(@PathVariable String deviceId, @PathVariable String lotteryId) {
=======
    // ６、抽選応募
    // POST /lotteries/{lotteryId}/{deviceId}
    @PostMapping("/coupons/{deviceId}/{couponId}")
    public BaseRes postCouponsDeviceId(@PathVariable String deviceId, @PathVariable String couponId) {
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git

<<<<<<< HEAD
		webAPIService.postCouponsDeviceId(deviceId, lotteryId);
=======
        webAPIService.postCouponsDeviceId(deviceId,couponId);
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git

<<<<<<< HEAD
		return BaseRes.SUCCESS;
	}

	// ７、抽選履歴取得
	// GET /lotteries/histories/{deviceId}
	@GetMapping("/lotteries/histories/{deviceId}")
	public BaseRes getCouponsHistories(@PathVariable String deviceId) {
=======
        return BaseRes.SUCCESS;
    }
    // ７、抽選履歴取得
    // GET /lotteries/histories/{deviceId}
    @GetMapping("/lotteries/histories/{deviceId}")
    public BaseRes getCouponsHistories(@PathVariable String deviceId) {
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git

		webAPIService.getCouponsHistories(deviceId);

<<<<<<< HEAD
		return BaseRes.SUCCESS;
	}

	// ８、フィードバック
	// POST /feedbacks/{deviceId}
	@PostMapping("/feedbacks/{deviceId}")
	public BaseRes postFeedbacks(@PathVariable String deviceId, @RequestBody PostFeedbacksReq req) {
=======
        return BaseRes.SUCCESS;
    }
    
    
    // ８、フィードバック
    // POST /feedbacks/{deviceId}
    @PostMapping("/synchronization/{deviceId}")
    public SynchronizationRes postFeedbacks(@PathVariable String deviceId, @RequestBody SynchronizationReq req) {
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git

		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setType(req.getType());
		feedbackDto.setDetail(req.getDetail());

		webAPIService.postFeedbacks(deviceId, feedbackDto);

<<<<<<< HEAD
		return BaseRes.SUCCESS;

	}

=======
    }
    
>>>>>>> branch 'develop' of https://gitlab.com/sparkworks/Restaurant/restaurant-backoffice.git
}
