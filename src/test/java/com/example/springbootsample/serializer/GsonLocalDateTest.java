package com.example.springbootsample.serializer;

import com.example.springbootsample.configuration.gson.GsonLocalDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GsonLocalDateTest {
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
            .create();

    @Test
    void serializeAndDeserializeTest() {
        LocalDate localDate = LocalDate.now();
        String json = gson.toJson(localDate);
        System.out.println(json);

        LocalDate deserialize = gson.fromJson(json, LocalDate.class);
        System.out.println(deserialize);

        assertThat(json).isEqualTo(gson.toJson(deserialize.toString()));
    }
}
