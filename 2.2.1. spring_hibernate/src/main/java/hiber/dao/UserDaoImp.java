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

   private static final String HQL="select id, firstName, lastName, email from User user " +
           "where user.car.model =:model and user.car.series = :series";


   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   public void hql(String model, int series) {
   List<Object[]> result = sessionFactory
           .getCurrentSession()
           .createQuery(HQL, Object[].class)
           .setParameter("model", model)
           .setParameter("series", series)
           .getResultList();
      for (Object[] u : result) {
         System.out.println("Id = " + (Long) u[0]);
         System.out.println("First Name = " + (String) u[1]);
         System.out.println("Last Name = " + (String) u[2]);
         System.out.println("Email = " + (String) u[3]);
      }
   }
}
