package jp.co.sparkworks.restaurant.backoffice.controller.api.param;

import lombok.Data;

@Data
public class PostFeedbacksReq {
	private String type;
	private String detail;
}
