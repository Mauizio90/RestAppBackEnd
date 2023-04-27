package eu.mauizio90.RestAppMaurizio.repositories;

import eu.mauizio90.RestAppMaurizio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauiz
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
}
