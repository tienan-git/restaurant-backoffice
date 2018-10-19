package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class CustomerFavoriteDto {
	Long restaurantId;
	String restaurantName;
	String createDatetime;
}
