package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);



      User user2 = context.getBean(User.class);

      user2.setFirstName("User1");
      user2.setLastName("Lastname1");
      user2.setEmail("user1@mail.ru");
      user2.setCar(new Car("Audi", 7));
      userService.add(user2);

      user2.setFirstName("User2");
      user2.setLastName("Lastname2");
      user2.setEmail("user2@mail.ru");
      user2.setCar(new Car("Volkswagen", 4));
      userService.add(user2);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      userService.hql("Volkswagen", 4);

      context.close();
   }
}
