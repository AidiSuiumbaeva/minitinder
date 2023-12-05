package kg.megacom.minitinder.utils;

import kg.megacom.minitinder.models.enums.Language;

import java.util.Locale;

public class ResourceBundle {
    private ResourceBundle(){}

    private static final java.util.ResourceBundle messagesRu =
            java.util.ResourceBundle.getBundle("message_ru",
                    Locale.forLanguageTag("ru"));

    private static final java.util.ResourceBundle messagesKg =
            java.util.ResourceBundle.getBundle("message_kg",
                    Locale.forLanguageTag("kg"));

    public static String periodMessages(String key, Language language) {

        switch (language){
            case KG:
                return messagesKg.getString(key);
            case RU:
            default:
                return messagesRu.getString(key);
        }

        }

}
