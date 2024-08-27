package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("Елена", "Наумова", "user1@mail.ru", new Car("Mazda", 6)));
        userService.add(new User("Андрей", "Егоров", "user2@mail.ru", new Car("Honda", 7)));
        userService.add(new User("Александр", "Иванов", "user3@mail.ru", new Car("Toyota", 200)));
        userService.add(new User("Артём", "Киселёв", "user4@mail.ru", new Car("Nissan", 9)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println("Владелец авто: " + carService.searchUser("Toyota", 200));
        context.close();
    }
}