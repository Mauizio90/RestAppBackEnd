package eu.mauizio90.RestAppMaurizio.services;

import eu.mauizio90.RestAppMaurizio.entities.User;
import eu.mauizio90.RestAppMaurizio.repositories.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mauiz
 */
@Service
public class UserDaoService {
    
    @Autowired
    private UserRepo userRepo;
    
    public User addUser(User user){
       return userRepo.save(user);
    }

    public List<User> findAll(){
        return  userRepo.findAll();
    }
    
    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }
    
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
}
