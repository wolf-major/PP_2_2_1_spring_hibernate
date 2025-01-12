package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @OneToOne (cascade = CascadeType.ALL)
   @JoinColumn(name = "car_id", referencedColumnName = "id")
   private Car userCar;

   public User() {}
   
   public User(String firstName, String lastName, String email, Car userCar) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.userCar = userCar;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Car getUserCar() {
      return userCar;
   }

   @Override
   public String toString() {
      return "Пользователь: " +
              "firstName = " + firstName + ", lastName = " + lastName +
              ", email ='" + email;
   }


   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id)
              && Objects.equals(firstName, user.firstName)
              && Objects.equals(lastName, user.lastName)
              && Objects.equals(email, user.email)
              && Objects.equals(userCar, user.userCar);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, userCar);
   }
}
