package jp.co.sparkworks.restaurant.backoffice.controller.param;

import lombok.Data;

@Data
public class PostFeedbacksReq {
	private String type;
	private String detail;
}
