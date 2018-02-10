package br.dev.oliveira.jsonplaceholderclient.models;


import java.io.Serializable;

public class Geo implements Serializable {
    private Float lat, lng;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
