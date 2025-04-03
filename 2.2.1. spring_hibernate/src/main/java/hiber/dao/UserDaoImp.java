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

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
   public String hql(String model, int series) {

      String firstName = "";
      String lastName = "";
      String email = "";

      String HQL2="select firstName, lastName, email from User user " +
              "where user.car.model =:model and user.car.series = :series";

      List<Object[]> result = sessionFactory
              .getCurrentSession()
              .createQuery(HQL2, Object[].class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
      for (Object[] u : result) {
         firstName = "firstName  = " + ((String) u[0]);
         lastName = "lastName  = " + ((String) u[1]);
         email = "email = " + ((String) u[2]);
      }
      return  firstName + "\n"  + lastName  + "\n" + email;
   }



}
