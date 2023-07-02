package com.royal.reserve.bank.account.api.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * Custom serializer for BigDecimal values.
 */
public class CustomBigDecimalRedisSerializer implements RedisSerializer<Object> {

    private final RedisSerializer<Object> jsonSerializer;

    /**
     * Creates a new instance of CustomBigDecimalRedisSerializer.
     *
     * @param jsonSerializer The JSON serializer.
     */
    public CustomBigDecimalRedisSerializer(RedisSerializer<Object> jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    /**
     * Serializes the given value to a byte array.
     *
     * @param value The value to serialize.
     * @return The serialized value.
     * @throws SerializationException If the value cannot be serialized.
     */
    @Override
    public byte[] serialize(Object value) throws SerializationException {
        if (value instanceof BigDecimal) {
            return value.toString().getBytes(StandardCharsets.UTF_8);
        }
        return jsonSerializer.serialize(value);
    }

    /**
     * Deserializes the given byte array to an object.
     *
     * @param bytes The byte array to deserialize.
     * @return The deserialized object.
     * @throws SerializationException If the byte array cannot be deserialized.
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }

        try {
            return new BigDecimal(new String(bytes, StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            return jsonSerializer.deserialize(bytes);
        }
    }
}

