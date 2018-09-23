package jp.co.sparkworks.restaurant.api.controller.param;

import lombok.Data;

@Data
public class PostFeedbacksReq {
	private String type;
	private String detail;
}
