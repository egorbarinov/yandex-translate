package ru.egorbarinov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TranslateRequest {
    private String folderId;
    private List<String> texts;
    private String sourceLanguageCode;
    private String targetLanguageCode;

    public TranslateRequest(String folderId, List<String> texts, String sourceLanguageCode, String targetLanguageCode) {
        this.folderId = folderId;
        this.texts = texts;
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
    }
}
