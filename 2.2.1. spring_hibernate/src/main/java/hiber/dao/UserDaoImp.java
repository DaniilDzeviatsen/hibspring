package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCarModelAndSeries(int series, String model) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where series=:series and model=:model");
        query.setParameter("series", series);
        query.setParameter("model", model);
        Car car = query.getSingleResult();
        long userId = car.getUser().getId();
        TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery("from User where id=:userId");
        userQuery.setParameter("userId", userId);
        return userQuery.getResultList();
    }

    //В этом методе получаю список юзеров у которых определенная марка авто (марка может быть у нескольких юзеров, поэтому список)
    @Override
    public List<User> getUserByCarModel(String model) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model=:model");
        query.setParameter("model", model);
        List<Car> cars = query.getResultList();
        List<Long> userIds = new ArrayList<>();
        for (Car car : cars) {
            userIds.add(car.getUser().getId());
        }
        Query<User> users = sessionFactory.getCurrentSession().createQuery("from User where id in (:usersIds_list)", User.class);
        users.setParameterList("usersIds_list", userIds);
        return users.list();
    }

    //в этом методе предположил, что серия уникальная (как VIN), соответственно результат будет один(не лист)
    //P.S. просто не совсем понял, что именно нужно было сделать, и решил сделать так, чтобы методы отличались
    @Override
    public User getUserByCarSeries(int series) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where series=:series");
        query.setParameter("series", series);
        Car car = query.getSingleResult();
        long userId = car.getUser().getId();
        TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery("from User where id=:userId");
        userQuery.setParameter("userId", userId);
        return userQuery.getSingleResult();
    }
}
