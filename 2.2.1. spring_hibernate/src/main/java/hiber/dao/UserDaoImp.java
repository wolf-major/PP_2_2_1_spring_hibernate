package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImp implements UserDao {

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

   @Override
   public String getUserByModelAndSeries(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("FROM User " +
                      "WHERE Car.model = :model " +
                      "AND Car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult().toString();
   }
}
