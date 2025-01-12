package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImp implements UserDao {
   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
   }

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

   @Override
   public String getUserByModelAndSeries(String model, int series) {
         Query query = sessionFactory.getCurrentSession()
                 .createQuery(" SELECT user " +
                         "FROM User user " +
                         "WHERE user.userCar.model = :model " +
                         "AND user.userCar.series = :series");
         query.setParameter("model", model);
         query.setParameter("series", series);
         List<User> listOfUsers = query.getResultList();
         return !listOfUsers.isEmpty()
                 ? listOfUsers.toString()
                 : "---\nПользователь с такой машиной не найден! Проверьте введенные данные.\n---";
   }
}
