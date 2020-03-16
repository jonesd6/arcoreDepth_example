package com.matt.arcore.java.sharedcamera_example;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.media.Image;
import android.media.ImageReader;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

public class TOF_ImageReader implements ImageReader.OnImageAvailableListener {
    private static final String TAG = TOF_ImageReader.class.getSimpleName();

    public static int WIDTH = 320;
    public static int HEIGHT = 240;

    public int frameCount;
    public long timestamp;

    public ByteBuffer depth16_raw;

    public DepthFrameVisualizer depthFrameVisualizer;

    public TOF_ImageReader() {
    }

    @Override
    public void onImageAvailable(ImageReader reader) {
        try {
            Image image = reader.acquireNextImage();
            if (image != null && image.getFormat() == ImageFormat.DEPTH16) {
                this.timestamp = image.getTimestamp();
                depth16_raw = image.getPlanes()[0].getBuffer().asReadOnlyBuffer();

                // copy raw undecoded DEPTH16 format depth data to NativeBuffer
                frameCount++;
            }
            image.close();
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to acquireNextImage: " + e.getMessage());
        }
    }

    private void processImage(Image image) {
        ShortBuffer shortDepthBuffer = image.getPlanes()[0].getBuffer().asShortBuffer();
    }

}