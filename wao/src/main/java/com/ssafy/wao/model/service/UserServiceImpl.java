package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dao.UserDao;
import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public int signup(UserDto userDto) {
        return userDao.signup(userDto);
    }

    @Override
    public UserDto login(UserDto userDto) {
        return userDao.login(userDto);
    }

    @Override
    public UserDto selectUser(int userNo) {
        return userDao.selectUser(userNo);
    }

    @Override
    public int modifyUser(UserDto userDto) {
        return userDao.modifyUser(userDto);
    }

    @Override
    public int modifyUserContent(UserDto userDto) {
        return userDao.modifyUserContent(userDto);
    }

    @Override
    public int modifyUserRegion(UserDto userDto) {
        return userDao.modifyUserRegion(userDto);
    }

    @Override
    public int modifyUserImg(UserDto userDto) { return userDao.modifyUserImg(userDto); };

    @Override
    public int unauthorize(UserDto userDto) {
        return userDao.unauthorize(userDto);
    }

    @Override
    public int authorize(UserDto userDto) {
        return userDao.authorize(userDto);
    }

    @Override
    public List<PartyDto> selectUserParties(int userNo) {
        return userDao.selectUserParties(userNo);
    }

    @Override
    public int validateUserInfo(UserDto userDto) {
        return (userDao.validateUserInfo(userDto) == null ? 0 : 1);
    }

    @Override
    public int isAuthorized(int userNo) {
        return userDao.isAuthorized(userNo);
    }

    @Override
    public List<EventDto> selectAllEvents(int userNo) {
        return userDao.selectAllEvents(userNo);
    }
}
