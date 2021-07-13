package ru.egorbarinov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.egorbarinov.dto.TranslateRequest;
import ru.egorbarinov.dto.TranslateResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class YandexTranslateApp {

    private final String YANDEX_TRANSLATE_API = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private final String FOLDER_ID = "b1g3oimj797clm0d239r";
    private final String BEARER_TOKEN = "t1.9euelZqYlsaTxo2QkpyTlsyXzpCclu3rnpWal4-azZSMiZvHyZeemp2bksvl9PcgJkt4-e8HXQDa3fT3YFRIePnvB10A2g.qYEdb6gezl-tpzeUPMmf7yBAGA4GKkJXLn2rt1fVBnSaXWp4hNtd7SjQko7Jf3uvqf0LWbxwjBjvxp8nrv9IBA";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String translate(String text, String fromLang, String targetLang) throws IOException {
        var mapper = new ObjectMapper();

        RequestBody body = null;
        try {
            body = RequestBody
                    .create(JSON, mapper.writeValueAsString(TranslateRequest.builder()
                            .folderId(FOLDER_ID)
                            .sourceLanguageCode(fromLang)
                            .targetLanguageCode(targetLang)
                            .texts(List.of(text))
                            .build()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var request = new Request.Builder()
                .url(YANDEX_TRANSLATE_API)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .post(body)
                .build();

        var response = client.newCall(request).execute();

        System.out.println(response.body().string());
        TranslateResponse result = mapper.readValue(Objects.requireNonNull(response.body()).string(), TranslateResponse.class);
        return result.getTranslations().get(0).getText();
    }

    public static void main(String[] args) throws IOException {
        var translateApp = new YandexTranslateApp();
        System.out.println(translateApp.translate("Hello, Vasya!", "en", "ru"));
    }
}
