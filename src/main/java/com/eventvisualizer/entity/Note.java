package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Note")
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "event_id")
    private int eventId;

    @Lob
    @Column(name = "note_description")
    @JsonProperty("noteDescription")
    private String noteDescription;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Event event;


    public Note() {
    }

    public Note(String noteDescription) {
        this.noteDescription = noteDescription;
    }


    public int getEventId() {
        return eventId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
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
        Note note = (Note) o;
        return Objects.equals(eventId, note.eventId) && Objects.equals(noteDescription, note.noteDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, noteDescription);
    }

    @Override
    public String toString() {
        return "Note{" +
                "eventId=" + eventId +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }
}