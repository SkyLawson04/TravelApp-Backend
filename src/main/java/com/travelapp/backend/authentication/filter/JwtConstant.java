package com.travelapp.backend.authentication.filter;

public class JwtConstant {
    public static final String JWT_SECRET = "ca3de3b8a1622fe1b441011f2e8a5158750971807bae61513b0d82aeb6265c5545df54d446ab93ba9da2f6a4a26d62a7b10a7928ce1baba80ecc3f303a19b9cad9ca7dbf1567072ee43504473ba77962685b83e1a8f3c59d434bd0e469daa09ac095f3acdca32c6e165378c7a54d50aab7697082ca1f9bf560e0a4ff06930f9e0e32535564583e06f8a16d7d1a7ce03f68033bdf5f35a606508264bfcf6fde1d07ec5d8fbaccc5fa4ed53772f70ffcc947f52c2585a79858a5caa8ad14a72299beaebc6f3e63fbf86bbd26004bbd11c915bb8ecb5d5ebce0dca7f68df6ff58d264af13a785cc7a1b588dc236ba2a3d157f4b8d13452d8a68d1347ed02cfbf1ec";
    public static final String JWT_PREFIX = "Bearer ";
    public static final String JWT_HEADER = "Authorization";
    public static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000;
}
