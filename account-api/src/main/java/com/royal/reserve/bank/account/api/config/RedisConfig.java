package com.royal.reserve.bank.account.api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.serializer.CustomBigDecimalRedisSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.util.List;

/**
 * Configuration class for Redis.
 */
@Getter
@Setter
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    /**
     * Creates a Redis template for storing and retrieving account responses.
     *
     * @param connectionFactory The Redis connection factory.
     * @return The Redis template for account responses.
     */
    @Bean
    public RedisTemplate<String, List<AccountResponse>> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<AccountResponse>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(createValueSerializer());

        return redisTemplate;
    }

    /**
     * Creates a custom serializer for BigDecimal values.
     *
     * @return The serializer for BigDecimal values.
     */
    private RedisSerializer<Object> createValueSerializer() {
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper());
        return new CustomBigDecimalRedisSerializer(jsonSerializer);
    }

    /**
     * Creates and configures an instance of ObjectMapper for JSON serialization and deserialization.
     *
     * @return The configured ObjectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    /**
     * Creates a Redis connection factory.
     *
     * @return The Redis connection factory.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(redisHost);
        standaloneConfiguration.setPort(redisPort);

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
        connectionFactory.afterPropertiesSet();

        return connectionFactory;
    }
}

