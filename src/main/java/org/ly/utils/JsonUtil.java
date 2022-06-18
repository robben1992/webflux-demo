package org.ly.utils;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static final ObjectMapper mapper = JsonMapper.builder()
            .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(JsonReadFeature.ALLOW_TRAILING_COMMA, true)
            .addModule(new JavaTimeModule())
            .build()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    @SneakyThrows
    public static JsonNode parse(String json) {
        return mapper.readTree(json);
    }

    @SneakyThrows
    public static <T> T toEntity(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> T toEntity(String json, TypeReference<T> type) {
        return mapper.readValue(json, type);
    }

    @SneakyThrows
    public static <T> List<T> toList(String json, Class<T> clazz) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        return mapper.readValue(json, type);
    }

    @SneakyThrows
    public static <T> List<T> toListByFile(File jsonFile, Class<T> clazz) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        return mapper.readValue(jsonFile, type);
    }

    @SneakyThrows
    public static String toJson(Object object) {
        return mapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T convert(Object fromValue, Class<T> clazz) {
        return mapper.convertValue(fromValue, clazz);
    }

    @SneakyThrows
    public static <T> T convert(Object fromValue, TypeReference<T> type) {
        return mapper.convertValue(fromValue, type);
    }
}