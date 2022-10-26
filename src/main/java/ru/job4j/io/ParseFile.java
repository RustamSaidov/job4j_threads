package ru.job4j.io;

import java.io.*;

public final class ParseFile {
    private final File file;
    private final GetContentStrategy getContentStrategy;

    public ParseFile(File file, GetContentStrategy getContentStrategy) {
        this.file = file;
        this.getContentStrategy = getContentStrategy;
    }

    public String getContent() {
        return getContentStrategy.getContent(file);
    }
}

