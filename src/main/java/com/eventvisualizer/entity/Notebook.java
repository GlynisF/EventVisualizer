package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Notebook.
 */
@Entity(name = "Notebook")
@Table(name = "notebook")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JsonBackReference(value = "user-notebook")
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "notebook_fk")
    )
    private User user;

    @OneToMany(mappedBy="notebook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "notebook-event")
    private final Set<Event> events = new LinkedHashSet<>();

    /**
     * Instantiates a new Notebook.
     */
    public Notebook() {

    }

    /**
     * Instantiates a new Notebook.
     *
     * @param title the title
     */
    public Notebook(String title) {
        this.title = title;
    }

    public Notebook newNotebookHelper(Notebook notebook) {
        return new Notebook(notebook.getTitle());
    }

    /**
     * Instantiates a new Notebook.
     *
     * @param title the title
     * @param user  the user
     */
    public Notebook(String title, User user) {
        this.title = title;
        this.user = user;
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setNotebook(this);
    }

    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(Event event) {
        events.remove(event);
        event.setNotebook(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return id == notebook.id && Objects.equals(title, notebook.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", user=" + (user != null ? user.getId() : "null")  +
                '}';
    }
}