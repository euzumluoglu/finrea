package de.reach;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {


    /**
     * What resource id must the OAuth2 client have to access
     * resources exposed by this resource server.
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("blacklist");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().and() // necessary for anonymous call to permitAll paths
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }


}
