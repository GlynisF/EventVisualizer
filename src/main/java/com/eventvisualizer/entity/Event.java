package com.eventvisualizer.entity;

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
    private String eventName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notebook_id",
            foreignKey = @ForeignKey(name = "event_notebook_fk")
    )
    private Notebook notebook;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Detail> details  = new LinkedHashSet<>();

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Goal goal;



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
                ", goal=" + goal +
                '}';
    }
}
