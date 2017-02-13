package com.martins.joao.tictactoedroid.utils;

import android.graphics.Bitmap;

import java.util.TreeMap;

/**
 * Created by João on 12/02/2017.
 */

public enum ResourceManager {
    INSTANCE;

    public TreeMap<String, Bitmap> images = new TreeMap<>();

    public void addImage(String key, Bitmap bmp) {
        images.put(key, bmp);
    }

    public Bitmap getImage(String key) {
        return images.get(key);
    }
}
