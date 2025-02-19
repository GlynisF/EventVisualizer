package com.eventvisualizer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

/**
 * The type Event.
 */
@Entity(name = "Event")
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "event_name")
    private String eventName;

    @ManyToOne
    @JoinColumn(name = "notebook_id",
            foreignKey = @ForeignKey(name = "event_notebook_fk")
    )
    private Notebook notebook;


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
                ", notebook=" + notebook +
                '}';
    }
}
