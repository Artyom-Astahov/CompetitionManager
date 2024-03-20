package by.artem.dao.classes;

import lombok.Getter;

@Getter
public enum SportCategoryEnum {
    MS("МС"),
    KMS("КМС"),
    FIRST_CATEGORY("1 разряд"),
    SECOND_CATEGORY("2 разряд"),
    THIRD_CATEGORY("3 разряд");

    private final String title;

    SportCategoryEnum(String title) {
        this.title = title;
    }


}
