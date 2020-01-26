package net.bitsrl.academia;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan({"net.bitsrl.academia"})
public class AcademiaSpringConfig {

}
