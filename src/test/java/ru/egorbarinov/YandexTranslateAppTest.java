package ru.egorbarinov;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class YandexTranslateAppTest {

    @Test
    public void test() throws IOException {
        YandexTranslateApp yandexTranslator = new YandexTranslateApp();
        assertEquals("Привет, Мир!", yandexTranslator.translate("Hello, World!", "en", "ru"));
    }
}
