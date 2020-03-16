package com.matt.arcore.java.sharedcamera_example;

import java.nio.ByteBuffer;

public interface DepthFrameVisualizer {
    void passDepthBuffer(ByteBuffer depthBuffer);
}
