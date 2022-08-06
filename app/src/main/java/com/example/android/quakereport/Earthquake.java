package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliSeconds;
    private String mUrl;

    public Earthquake(double Magnitude, String Location, long TimeInMilliSeconds, String URL){
        mMagnitude = Magnitude;
        mLocation = Location;
        mTimeInMilliSeconds = TimeInMilliSeconds;
        mUrl = URL;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getmTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
