package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();


    List<User> getUserByCarModel(String model);

    User getUserByCarSeries(int series);
}
