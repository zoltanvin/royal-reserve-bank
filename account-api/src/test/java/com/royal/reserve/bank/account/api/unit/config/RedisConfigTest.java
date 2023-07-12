package com.royal.reserve.bank.account.api.unit.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.royal.reserve.bank.account.api.config.RedisConfig;
import com.royal.reserve.bank.account.api.dto.AccountResponse;
import com.royal.reserve.bank.account.api.serializer.CustomBigDecimalRedisSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link RedisConfig} class.
 */
class RedisConfigTest {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    private RedisConfig redisConfig;

    @Mock
    private RedisConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        redisConfig = new RedisConfig();
        connectionFactory = mock(RedisConnectionFactory.class);
    }

    /**
     * Test for {@link RedisConfig#redisTemplate(RedisConnectionFactory)} method.
     */
    @Test
    void testRedisTemplate() {
        // Given
        RedisTemplate<String, List<AccountResponse>> redisTemplate = redisConfig.redisTemplate(connectionFactory);
        StringRedisSerializer keySerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
        CustomBigDecimalRedisSerializer valueSerializer =
                (CustomBigDecimalRedisSerializer) redisTemplate.getValueSerializer();

        // When and Then
        assertEquals(connectionFactory, redisTemplate.getConnectionFactory());
        assertEquals(StringRedisSerializer.class, keySerializer.getClass());
        assertEquals(CustomBigDecimalRedisSerializer.class, valueSerializer.getClass());
    }

    /**
     * Test for {@link RedisConfig#objectMapper()} method.
     */
    @Test
    void shouldConfigureObjectMapperCorrectly() {
        // Given
        ObjectMapper objectMapper = redisConfig.objectMapper();

        // When and Then
        assertFalse(objectMapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        assertFalse(objectMapper.getSerializationConfig().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
    }

    /**
     * Test for {@link RedisConfig#redisConnectionFactory()} method.
     */
    @Test
    void testRedisConnectionFactory() {
        // Given
        String actualHost = redisConfig.getRedisHost();
        int actualPort = redisConfig.getRedisPort();

        // When and Then
        assertEquals(redisHost, actualHost);
        assertEquals(redisPort, actualPort);
    }

/*    @Test
    void testRedisConnectionFactory() {
        // Given
        RedisConnectionFactory redisConnectionFactory = redisConfig.redisConnectionFactory();
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisConnectionFactory;
        RedisStandaloneConfiguration redisConfig = lettuceConnectionFactory.getStandaloneConfiguration();

        // When and Then
        assertEquals("localhost", redisConfig.getHostName());
        assertEquals(6379, redisConfig.getPort());
    }*/
}
