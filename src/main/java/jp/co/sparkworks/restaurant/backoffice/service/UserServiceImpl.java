package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.opentone.arapp.backoffice.db.dao.UserDao;
import jp.co.opentone.arapp.backoffice.db.entity.User;
import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.UserCustomDao;
import jp.co.sparkworks.restaurant.backoffice.dto.UserDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserCustomDao userCustomDao;

    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public List<UserDto> search() {

        List<User> userList = userCustomDao.selectAll();

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user : userList) {

            UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setRoleId(user.getRoleId());
            userDto.setPassword(user.getPassword());
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {

        User existUser = userCustomDao.selectByEmail(userDto.getEmail());
        if (existUser != null) {
            throw new BusinessException(ErrorCodeConstant.E50011);
        }

        // DTO->Entity
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setRoleId(userDto.getRoleId());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDto.getPassword()));// パスワードを暗号化する

        // DB access
        userDao.insert(user);

        // Entity->DTO
        UserDto newUserDto = new UserDto();
        newUserDto.setUserId(user.getUserId());
        newUserDto.setUserName(user.getUserName());
        newUserDto.setEmail(user.getEmail());
        newUserDto.setRoleId(user.getRoleId());
        newUserDto.setPassword(user.getPassword());

        return newUserDto;
    }

    @Transactional
    @Override
    public UserDto getById(Long userId) {

        User user = userDao.selectById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCodeConstant.E50012);
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRoleId(user.getRoleId());
        userDto.setPassword(user.getPassword());

        return userDto;

    }

    @Transactional
    @Override
    public void update(UserDto userDto) {

        User user = userCustomDao.selectByEmail(userDto.getEmail());

        // 該当メールがすでに使われている場合
        if (user != null && !user.getUserId().equals(userDto.getUserId())) {
            throw new BusinessException(ErrorCodeConstant.E50011);
        }

        if (user == null) {
            user = userDao.selectById(userDto.getUserId());
        }

        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(userDto.getPassword()));
        }
        user.setRoleId(userDto.getRoleId());

        userDao.update(user);

    }

    @Transactional
    @Override
    public void delete(Long userId) {

        User user = userDao.selectById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCodeConstant.E50012);
        }
        userDao.delete(user);

    }

    @Transactional
    @Override
    public UserDto getByEmail(String email) {

        User user = userCustomDao.selectByEmail(email);
        if (user == null) {
            throw new BusinessException(ErrorCodeConstant.E50012);
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRoleId(user.getRoleId());
        userDto.setPassword(user.getPassword());

        return userDto;

    }

}
