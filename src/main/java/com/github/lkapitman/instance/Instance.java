package com.github.lkapitman.instance;

import com.github.lkapitman.ui.panel.IPanel;

public class Instance {

    private final String name;
    private final String url;
    private final String[] keyword;

    public Instance(String name, String url, String[] keyword) {
        this.name = name;
        this.url = url;
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String[] getKeyword() {
        return keyword;
    }
}
