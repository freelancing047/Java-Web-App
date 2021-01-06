package blueprint.auth.security;





import blueprint.auth.model.entity.User;
import blueprint.auth.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetialsService implements ClientDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ClientDetails loadClientByClientId(String username) throws ClientRegistrationException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return new CustomClientDetails(user);
        }
        return null;
    }

}
