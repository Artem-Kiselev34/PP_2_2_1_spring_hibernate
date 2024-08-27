package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CarServiceImp implements CarService {

    private final CarDao carDao;
    public final UserDao userDao;


    public CarServiceImp(CarDao carDao, UserDao userDao) {
        this.carDao = carDao;
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> searchUser(String model, int series) {
        return userDao.searchUser(model, series);
    }
}
