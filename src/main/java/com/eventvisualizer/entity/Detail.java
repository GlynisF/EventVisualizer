package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Detail.
 */
@Entity (name = "Details")
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_event")
    private LocalDate dateOfEvent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
    private LocalTime endTime;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",
            foreignKey = @ForeignKey(name = "notebook_fk"))
    private Event event;

    @OneToMany(mappedBy="performer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<Performer> performers  = new LinkedHashSet<>();


    /**
     * Instantiates a new Detail.
     */
    public Detail() {

    }

    /**
     * Instantiates a new Detail.
     *
     * @param dateOfEvent the date of event
     * @param startTime   the start time
     * @param endTime     the end time
     * @param description the description
     */
    public Detail(LocalDate dateOfEvent, LocalTime startTime, LocalTime endTime, String description) {
        this.dateOfEvent = dateOfEvent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
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
     * Gets date of event.
     *
     * @return the date of event
     */
    public LocalDate getDateOfEvent() {
        return dateOfEvent;
    }

    /**
     * Sets date of event.
     *
     * @param dateOfEvent the date of event
     */
    public void setDateOfEvent(LocalDate dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Performer> getPerformers() {
        return performers;
    }

    public void addPerformer(Performer performer) {
        performers.add(performer);
        performer.setDetail(this);
    }

    public void removePerformer(Performer performer) {
        performers.remove(performer);
        performer.setDetail(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return Objects.equals(id, detail.id) && Objects.equals(dateOfEvent, detail.dateOfEvent) && Objects.equals(startTime, detail.startTime) && Objects.equals(endTime, detail.endTime) && Objects.equals(description, detail.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfEvent, startTime, endTime, description);
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", dateOfEvent=" + dateOfEvent +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }
}