package blueprint.auth.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;



@Configuration

@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)

public class WebSecurityConfiguration extends ResourceServerConfigurerAdapter {

    private static final String[] PUBLIC_RESOURCES = {
            "/.well-known/jwks",
            "/uaa/currentUser",
            "/uaa/signup",
            "/oauth**",
            "/api/search/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( PUBLIC_RESOURCES).permitAll()
                .anyRequest().authenticated();
    }
}
