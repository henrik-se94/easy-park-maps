package com.sigma.easyparkmap.module;

public class Pointer {
    private double log;
    private double lat;

    public Pointer(double log, double lat) {
        this.log = log;
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
