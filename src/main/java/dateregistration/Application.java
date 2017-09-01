package dateregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by finawei on 8/14/17.
 */

@SpringBootApplication @ComponentScan({"dateregistration","login"})
public class Application {
    public static void main (String[] args){
        SpringApplication.run(Application.class,args);
    }
}
