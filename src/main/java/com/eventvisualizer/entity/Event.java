package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Event.
 */
@Entity(name = "Event")
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int id;

    @Column(name = "event_name")
    @JsonProperty("eventName")
    private String eventName;

    @ManyToOne
    @JsonBackReference(value = "notebook-event")
    @JoinColumn(name = "notebook_id",
            foreignKey = @ForeignKey(name = "event_notebook_fk")
    )
    private Notebook notebook;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "event-detail")
    private Set<Detail> details  = new LinkedHashSet<>();

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Note note;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Goal goal;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Reflection reflection;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "event-custom")
    private Set<Custom> customs = new LinkedHashSet<>();



    /**
     * Instantiates a new Event.
     */
    public Event() {


    }

    /**
     * Instantiates a new Event.
     *
     * @param eventName the event name
     */
    public Event(String eventName) {
        this.eventName = eventName;
    }

    public Event newEventHelper(Event event) {
        return new Event(event.getEventName());
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets event name.
     *
     * @param eventName the event name
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets notebook.
     *
     * @return the notebook
     */
    public Notebook getNotebook() {
        return notebook;
    }

    /**
     * Sets notebook.
     *
     * @param notebook the notebook
     */
    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public Set<Detail> getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(Set<Detail> details) {
        this.details = details;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Reflection getReflection() {
        return reflection;
    }

    public void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Set<Custom> getCustoms() {
        return customs;
    }

    public void setCustoms(Set<Custom> customs) {
        this.customs = customs;
    }

    /**
     * Add detail.
     *
     * @param detail the detail
     */
    public void addDetail(Detail detail) {
        details.add(detail);
        detail.setEvent(this);
    }

    /**
     * Remove detail.
     *
     * @param detail the detail
     */
    public void removeDetail(Detail detail) {
        details.remove(detail);
        detail.setEvent(null);
    }

    public void addCustom(Custom custom) {
        customs.add(custom);
        custom.setEvent(this);
    }

    public void removeCustom(Custom custom) {
        customs.remove(custom);
        custom.setEvent(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id && Objects.equals(eventName, event.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", notebook=" + (notebook != null ? notebook.getId() : "null") +
                '}';
    }
}