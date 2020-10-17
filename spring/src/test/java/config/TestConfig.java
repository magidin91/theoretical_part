package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Конфигурация для тестов
 */
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.education.jpa"}) // указываем, где репозитории Jpa
@ComponentScan (basePackages = {"ru.education.service.impl"}) // указываем спрингу, где искать компоненты - сервисы
@EntityScan(basePackages = {"ru.education.entity"}) // указываем, где искать сущности Jpa
public class TestConfig {
}
