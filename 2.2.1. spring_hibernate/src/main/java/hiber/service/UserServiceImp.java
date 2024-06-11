package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserByCarModelAndSeries(int series, String model) {
        return userDao.getUserByCarModelAndSeries(series, model);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserByCarModel(String model) {
        return userDao.getUserByCarModel(model);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByCarSeries(int series) {
        return userDao.getUserByCarSeries(series);
    }
}
