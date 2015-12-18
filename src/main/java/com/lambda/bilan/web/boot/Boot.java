package com.lambda.bilan.web.boot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;





@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.lambda.bilan.web" })
@Import(com.lambda.bilan.boot.Boot.class)

public class Boot {
	private static final String DATE_DEPART="2015-12-01";
	private static final String FORMAT="yyyy-MM-dd";
	private static final Long MOIS_EN_MILISECOND=2592000000L;
	
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Boot.class, args);
        
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		String dateInString = DATE_DEPART;
		Date date = sdf.parse(dateInString);

		Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	        public void run()
	        {
	        	//fonction sender here
	        }
	    };
	    timer.schedule( task,date ,MOIS_EN_MILISECOND);
    }
}
