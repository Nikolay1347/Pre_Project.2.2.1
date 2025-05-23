package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private static final String HQL = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";

   @Autowired
   private SessionFactory sessionFactory;

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
   public User getUser(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL, User.class);
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      return query.getSingleResult();
   }
}


