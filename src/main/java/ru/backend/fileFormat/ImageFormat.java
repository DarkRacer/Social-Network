package ru.backend.fileFormat;

import lombok.Getter;

public enum ImageFormat {
    Png("png"),
    Jpg("jpg"),
    Jpeg("jpeg");

    @Getter
    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }

}
