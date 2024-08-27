package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        if (user.getCar() != null) {
            session.save(user.getCar());
        }
        session.save(user);
    }

    @Override
    public List<User> searchUser(String model, int series) {
        Long carId = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series", Car.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult()
                .getId();

        return sessionFactory.getCurrentSession().createQuery("from User where car.id = :carid", User.class)
                .setParameter("carid", carId)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u join fetch u.car");
        return query.getResultList();
    }
}

