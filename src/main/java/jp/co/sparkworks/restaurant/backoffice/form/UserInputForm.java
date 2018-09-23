package jp.co.sparkworks.restaurant.backoffice.form;

import lombok.Data;

@Data
public class UserInputForm {
	Long userId;
	
	String userName;

	String email;
	
	String password;

	Long roleId;
}
