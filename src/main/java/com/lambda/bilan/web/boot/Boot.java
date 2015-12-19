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

import com.lambda.bilan.helpers.MailService;





@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.lambda.bilan.web" })
@Import(com.lambda.bilan.boot.Boot.class)

public class Boot {
	private static final String DATE_DEPART="2015-12-01 04:00:00.0";
	private static final String FORMAT="yyyy-MM-dd HH:mm:ss.SSS";
	private static final Long QUINZE_JOURS_EN_MILISECOND=1296000000L;
	
    public static void main(String[] args) throws ParseException {
    	ConfigurableApplicationContext context =  SpringApplication.run(Boot.class, args);
        
    	MailService mailMetier = context.getBean(MailService.class);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		String dateInString = DATE_DEPART;
		Date date = sdf.parse(dateInString);
		System.out.println(date.toString());
		Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	        public void run()
	        {
	        	//mailMetier.sendMailsManagerRH();
	        }
	    };
	    timer.schedule( task,date , QUINZE_JOURS_EN_MILISECOND);
    }
}
