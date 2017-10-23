package dateregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by finawei on 8/14/17.
 */

@SpringBootApplication @ComponentScan({"dateregistration","login","email"})
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class Application extends ResourceServerConfigurerAdapter {
    public static void main (String[] args){
        SpringApplication.run(Application.class,args);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers(HttpMethod.POST, "/user").permitAll();
               // .antMatchers(HttpMethod.GET,"/admin").hasRole("Admin");
    }
}
