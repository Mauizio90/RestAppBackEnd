package eu.mauizio90.RestAppMaurizio.controllers;

import eu.mauizio90.RestAppMaurizio.entities.Post;
import eu.mauizio90.RestAppMaurizio.exceptions.UserNotFoundException;
import eu.mauizio90.RestAppMaurizio.entities.User;
import eu.mauizio90.RestAppMaurizio.services.PostService;
import eu.mauizio90.RestAppMaurizio.services.UserDaoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author mauiz
 */
@RestController
public class UserResource {
    
    @Autowired
    private UserDaoService userDaoService;
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }
    
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable Long id){
        Optional<User> user = userDaoService.findById(id);
        
        if (user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }else if(user.get().getPosts().isEmpty()){
            throw new RuntimeException("Nessun Post");
        }
        
        return user.get().getPosts();
    }
    
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostsByUser(@PathVariable Long id, @Valid @RequestBody Post post){
        Optional<User> user = userDaoService.findById(id);
        
        if (user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }
        post.setUser(user.get());
        Post savedPost = postService.addPost(post);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/users/{id}")
    public Optional<User> retrieveUser(@PathVariable Long id){
        Optional<User> user = userDaoService.findById(id);
        
        if(user.isEmpty()){
            throw  new UserNotFoundException("id:"+id);
                    }
        
        return user;
        
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        Optional<User> user = userDaoService.findById(id);
        
        if(user.isEmpty()){
            throw  new UserNotFoundException("id:"+id);
                    }
        userDaoService.deleteById(id);
        
    }
    
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        
        User saveduser = userDaoService.addUser(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveduser.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
        
    }
}
