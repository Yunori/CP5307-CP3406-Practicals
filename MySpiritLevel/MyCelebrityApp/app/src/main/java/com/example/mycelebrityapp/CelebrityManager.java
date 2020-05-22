package com.example.mycelebrityapp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

class CelebrityManager
{
    private String assetPath;
    private String[] imageNames;
    private AssetManager assetManager;

    CelebrityManager(AssetManager assetManager, String assetPath)
    {
        this.assetManager = assetManager;
        this.assetPath = assetPath;

        try
        {
            imageNames = this.assetManager.list(assetPath);
        }
        catch (IOException e)
        {
            System.out.println("Not a valid asset path or path is empty");
        }

    }

    Bitmap get(int i)
    {
        try
        {
            InputStream stream = assetManager.open(assetPath + imageNames[i]);
            return BitmapFactory.decodeStream(stream);
        }
        catch (IOException e)
        {
            System.out.println("The file does not exist");
            return null;
        }
    }

    String getName(int i)
    {
        String str = imageNames[i];

        return str.substring(0, str.lastIndexOf('.'));
    }

    int count()
    {
        return imageNames.length;
    }
}
