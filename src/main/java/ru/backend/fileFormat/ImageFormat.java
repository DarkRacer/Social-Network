package ru.backend.fileFormat;

import lombok.Getter;

public enum ImageFormat {
    png("png"),
    jpg("jpg"),
    jpeg("jpeg");

    @Getter
    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }

}
