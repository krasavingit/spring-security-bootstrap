package ru.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {


    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username");
        query.setParameter("username", username);
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            // Handle exception
        }
        return user;
    }

    public List<User> getAllUsers(){
        return entityManager.createQuery( "from " + User.class.getName())
                .getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User findOne( long id ){
        return entityManager.find( User.class, id );
    }

    public void delete( User user ){
        entityManager.remove(user);
    }

    public void deleteById( long id ){
        delete(findOne(id));
    }

    public User update( User user ){
        return entityManager.merge(user);
    }
}
