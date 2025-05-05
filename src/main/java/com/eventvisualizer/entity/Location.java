package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity (name = "Location")
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int id;

    @Column(name = "location_name", length = 100)
    private String locationName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "address_2", length = 100)
    private String address2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "zip", length = 10)
    private String zip;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "`accessible`")
    private Boolean accessible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "detail-location")
    @JoinColumn(name = "detail_id",
    foreignKey = @ForeignKey(name = "location_detail_fk"))
    private Detail detail;

    public Location() {

    }

    public Location(String locationName, String phoneNumber, String address, String address2, String city, String state, String zip, String website, Boolean accessible) {
        this.locationName = locationName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.website = website;
        this.accessible = accessible;
    }


    public int getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && Objects.equals(locationName, location.locationName) && Objects.equals(phoneNumber, location.phoneNumber) && Objects.equals(address, location.address) && Objects.equals(address2, location.address2) && Objects.equals(city, location.city) && Objects.equals(state, location.state) && Objects.equals(zip, location.zip) && Objects.equals(website, location.website) && Objects.equals(accessible, location.accessible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationName, phoneNumber, address, address2, city, state, zip, website, accessible);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", website='" + website + '\'' +
                ", accessible=" + accessible +
                ", detail=" + (detail != null ? detail.getId() : "null") +
                '}';
    }
}