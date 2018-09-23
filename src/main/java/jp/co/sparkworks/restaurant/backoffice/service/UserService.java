package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.UserDto;

public interface UserService {

	List<UserDto> search();

	UserDto create(UserDto userDto);

	UserDto getById(Long userId);

	void update(UserDto userDto);

	void delete(Long userId);

	UserDto getByEmail(String email);
}
