package blueprint.auth.security;


import blueprint.auth.model.entity.Role;
import blueprint.auth.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * Provides a basic implementation of the UserDetails interface
 */
public class CustomClientDetails implements ClientDetails {

    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private String password;
    private String username;
    User user;

    public CustomClientDetails(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities=getAuthorities();
    }

    /**
     * Translates the List<Role> to a List<GrantedAuthority>
     * @param roles the input list of roles.
     * @return a list of granted authorities
     */


    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        if (user.getRoles().size() > 0) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                String name = role.getName().toUpperCase();
                //Make sure that all roles start with "ROLE_"
                if (!name.startsWith("ROLE_"))
                    name = "ROLE_" + name;
                authorities.add(new SimpleGrantedAuthority(name));
            }

            return authorities;
        }
        return null;
    }


    @Override
    public String getClientId() {
        return username;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return password;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> scopes = new HashSet<>();
        for (Role role : user.getRoles()) {
            String name = role.getName().toUpperCase();
            scopes.add(name);
        }
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> set=new HashSet<>();
        set.add("client_credentials");
        return set;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }


    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}