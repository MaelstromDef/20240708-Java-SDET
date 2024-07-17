package com.skillstorm.ahuggins_testing.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.skillstorm.ahuggins_testing.beans.Car;
import com.skillstorm.ahuggins_testing.beans.ElectricEngine;
import com.skillstorm.ahuggins_testing.beans.Engine;
import com.skillstorm.ahuggins_testing.beans.GasEngine;
import com.skillstorm.ahuggins_testing.beans.Vehicle;

@Configuration
public class CarConfiguration {
    /*
     * Tells Spring where to get our beans from/how to initialize them.
     * 
     * @Bean
     *      - Registers the bean inside the BeanFactory.
     *      - This way, Spring can give you beans when you ask for them.
     * 
     * @Scope
     *      - singleton     - each bean will be the same (default)
     *      - prototype     - each bean will be different, created from a base.
     *      - application   - creates a bean for the lifespan of the entire application
     *      - request       - creates a bean for the lifespan of an HTTP request
     *      - session       - creates a bean for the lifespan of a user's session
     *      - websocket     - creates a bean for the lifespan of a websocket
     * 
     */

     @Bean(name={"mustang", "camero"})
     @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
     public Vehicle gasCar(){
        Car car = new Car();
        car.setEngine(gasEngine()); // setter injection
        return car;
     }

     @Bean(name="tesla")
     // @Scope("singleton")
     public Vehicle electricCar(){
        Car car = new Car(electricEngine());    // constructor injection
        return car;
     }

     @Bean
     public Engine gasEngine(){
        return new GasEngine();
     }

     @Bean
     public Engine electricEngine(){
        return new ElectricEngine();
     }
}
