package com.example.bookmark.config;

import java.util.ResourceBundle;

public class ResourcesBo {
    public static String getKey(String key)
    {
        return ResourceBundle.getBundle("database").getString(key);
    }
}
