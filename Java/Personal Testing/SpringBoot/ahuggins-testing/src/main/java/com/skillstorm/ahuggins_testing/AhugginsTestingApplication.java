package com.skillstorm.ahuggins_testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.skillstorm.ahuggins_testing.beans.Vehicle;

/*
 * @SpringBootApplication is a combo of 3 annotations:
* 		@Configuration	-	Specifies that a class will be a configuration.
 * 							More specifically, indicates that this class will 
 * 							have beans to manage.
 * 		@ComponentScan	-	Searches your package for any class annotated with
 * 							@Component and make beans for those components.
 * 		@EnableAutoConfiguration	-	Tells Spring to auto-configure the app
 * 										context with things we need (like a 
 * 										server for a web app).
 */

@SpringBootApplication
public class AhugginsTestingApplication implements CommandLineRunner{

	@Autowired
	@Qualifier("camero")
	private Vehicle car3;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AhugginsTestingApplication.class, args);
		Vehicle car = (Vehicle)context.getBean("tesla");
		car.drive();

		Vehicle car2 = (Vehicle)context.getBean("tesla");
		
		Vehicle car4 = (Vehicle)context.getBean("mustang");

		System.out.println((car.equals(car2)));
		//System.out.println((car3.equals(car4)));
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running");
		car3.drive();
	}
}
