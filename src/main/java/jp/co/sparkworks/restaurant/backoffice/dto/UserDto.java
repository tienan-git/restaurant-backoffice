package jp.co.sparkworks.restaurant.backoffice.dto;


import lombok.Data;

@Data
public class UserDto {
	Long userId;
	String userName;
	String email;
	Long roleId;
	String password;
}
