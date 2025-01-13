package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("Tesla", 3));

      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru", new Car("Mazda", 5));

      userService.add(user2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 3));

      userService.add(user3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru", new Car("LADA", 2113));

      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car's model = " + user.getUserCar().getModel());
         System.out.println("Car's series = " + user.getUserCar().getSeries());
         System.out.println();
      }

      String modelUser = "BMW";
      int seriesUser = 3;

      System.out.printf("Владелец(цы) автомобиля марки %s серии %s:\n", modelUser, seriesUser);
      System.out.println(userService.getUserByModelAndSeries(modelUser, seriesUser));

      context.close();
   }
}
