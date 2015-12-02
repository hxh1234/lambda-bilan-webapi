package com.lambda.bilan.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;





@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.lambda.bilan.web" })
@Import(com.lambda.bilan.boot.Boot.class)

public class Boot {

    public static void main(String[] args) {
        //SpringApplication.run(Boot.class, args);
    	
    	SpringApplication app = new SpringApplication(Boot.class);
		app.setLogStartupInfo(false);
		// on la lance
		ConfigurableApplicationContext context = app.run(args);
		// métier
		//Metier metier = context.getBean(Metier.class);
		
		//metier.getuser();
        
    }
}
