package eu.mauizio90.RestAppMaurizio.repositories;

import eu.mauizio90.RestAppMaurizio.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mauiz
 */
@Repository
public interface PostRepo extends JpaRepository<Post, Long>{
    
}
