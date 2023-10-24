package uc.dei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//nothing more should be needed this just start the server application, all other files is where the bulk of the work is

@SpringBootApplication
public class SpringReactiveServerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringReactiveServerApp.class, args);
    }
}
