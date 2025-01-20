package com.tuusuario.pizzetos;

import com.google.gson.annotations.SerializedName;

public class TranslationResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @SerializedName("translations")
        private Translation[] translations;

        public Translation[] getTranslations() {
            return translations;
        }
    }

    public static class Translation {
        @SerializedName("translatedText")
        private String translatedText;

        public String getTranslatedText() {
            return translatedText;
        }
    }
}
