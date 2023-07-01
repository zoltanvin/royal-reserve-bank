package com.royal.reserve.bank.account.api.unit.serializer;

import com.royal.reserve.bank.account.api.serializer.CustomBigDecimalRedisSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CustomBigDecimalRedisSerializer} class.
 */
class CustomBigDecimalRedisSerializerTest {
    private CustomBigDecimalRedisSerializer serializer;

    @Mock
    private RedisSerializer<Object> jsonSerializer;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        jsonSerializer = mock(RedisSerializer.class);
        serializer = new CustomBigDecimalRedisSerializer(jsonSerializer);
    }

    /**
     * Test the constructors.
     */
    @Test
    void serializeWithBigDecimal() {
        // Given
        BigDecimal value = new BigDecimal("10.50");
        byte[] expectedBytes = "10.50".getBytes();

        // When
        byte[] result = serializer.serialize(value);

        // Then
        assertArrayEquals(expectedBytes, result);
    }

    @Test
    void serializeWithNonBigDecimal() {
        // Given
        Object value = "test";
        byte[] expectedBytes = "serialized".getBytes();
        when(jsonSerializer.serialize(value)).thenReturn(expectedBytes);

        // When
        byte[] result = serializer.serialize(value);

        // Then
        assertArrayEquals(expectedBytes, result);
    }

    @Test
    void deserializeWithValidBytes() {
        // Given
        byte[] bytes = "10.50".getBytes();
        BigDecimal expectedValue = new BigDecimal("10.50");

        // When
        Object result = serializer.deserialize(bytes);

        // Then
        assertEquals(expectedValue, result);
    }

    @Test
    void deserializeWithInvalidBytes() {
        // Given
        byte[] bytes = "invalid".getBytes();
        Object expectedValue = "deserialized";
        when(jsonSerializer.deserialize(bytes)).thenReturn(expectedValue);

        // When
        Object result = serializer.deserialize(bytes);

        // Then
        assertEquals(expectedValue, result);
    }

    @Test
    void deserializeWithNullBytes() {
        // When
        Object result = serializer.deserialize(null);

        // Then
        assertNull(result);
    }
}
