package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CarDaoImp implements CarDao{

    private final SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car){
        sessionFactory.getCurrentSession().save(car);
    }
    @Override
    public User searchUser(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery("from User where car.id = :carid", User.class)
                .setParameter(
                        "carid",
                        sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series", Car.class)
                                .setParameter("model", model)
                                .setParameter("series", series)
                                .getSingleResult()
                                .getId())
                .getSingleResult();
    }
}
