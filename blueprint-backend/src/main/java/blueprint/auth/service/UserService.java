package blueprint.auth.service;




import blueprint.auth.model.entity.User;
import blueprint.auth.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


   UserRepository repo;
    @Autowired
    public UserService(UserRepository repo){
        this.repo=repo;
    }
    public void save(User user){
        repo.save(user);
    }
    public User findByUserName(String userName){
        return repo.findByUsername(userName);
    }





}
