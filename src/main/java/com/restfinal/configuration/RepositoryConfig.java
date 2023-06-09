package com.restfinal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.restfinal.repositories.jpa")
@EnableRedisRepositories(basePackages = "com.restfinal.repositories.redis")
public class RepositoryConfig {
}
