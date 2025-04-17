package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity (name = "Reflection")
@Table(name = "reflection")
public class Reflection {

    @Id
    @Column(name = "event_id")
    private int eventId;

    @Lob
    @Column(name = "reflection_description")
    @JsonProperty("reflectionDescription")
    private String reflectionDescription;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Event event;


    public Reflection() {
    }

    public Reflection(String reflectionDescription) {
        this.reflectionDescription = reflectionDescription;
    }

    public Reflection newReflectionHelper(Reflection reflection) {
        return new Reflection(reflection.getReflectionDescription());
    }

    public int getEventId() {
        return eventId;
    }


    public String getReflectionDescription() {
        return reflectionDescription;
    }

    public void setReflectionDescription(String reflectionDescription) {
        this.reflectionDescription = reflectionDescription;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reflection that = (Reflection) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(reflectionDescription, that.reflectionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, reflectionDescription);
    }

    @Override
    public String toString() {
        return "Reflection{" +
                "eventId=" + eventId +
                ", reflectionDescription='" + reflectionDescription + '\'' +
                '}';
    }
}