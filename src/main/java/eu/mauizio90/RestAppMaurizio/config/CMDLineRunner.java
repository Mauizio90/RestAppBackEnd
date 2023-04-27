package eu.mauizio90.RestAppMaurizio.config;

import eu.mauizio90.RestAppMaurizio.entities.Post;
import eu.mauizio90.RestAppMaurizio.entities.User;
import eu.mauizio90.RestAppMaurizio.services.PostService;
import eu.mauizio90.RestAppMaurizio.services.UserDaoService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author mauiz
 */
@Component
public class CMDLineRunner implements CommandLineRunner{

    @Autowired
    private UserDaoService userDaoService;
    
    @Autowired
    private PostService postService;
    
    @Override
    public void run(String... args) throws Exception {
        User maurizio = new User("Maurizio",LocalDate.of(1990, 6, 28));
        User peter = new User("Peter",LocalDate.of(1990, 1, 28));
        User logan = new User("Logan",LocalDate.of(1985, 1, 13));
        
        userDaoService.addUser(maurizio);
        userDaoService.addUser(peter);
        userDaoService.addUser(logan);
        
        postService.addPost(new Post("POPPOpost",maurizio));
        postService.addPost(new Post("POPPOpost2",maurizio));
        postService.addPost(new Post("POPPOpost3",maurizio));
    }
    
}
