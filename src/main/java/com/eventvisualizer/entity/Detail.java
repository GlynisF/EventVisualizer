package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Detail.
 */
@Setter
@Getter
@Entity (name = "Details")
@Table(name = "detail")
public class Detail {

    /**
     * -- GETTER --
     *  Gets id.
     *
     *
     * -- SETTER --
     *  Sets id.
     *
     @return the id
      * @param id the id
     */
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private int id;

    /**
     * -- GETTER --
     *  Gets date of event.
     *
     *
     * -- SETTER --
     *  Sets date of event.
     *
     @return the date of event
      * @param dateOfEvent the date of event
     */
    @Setter
    @Column(name = "date_of_event")
    @JsonProperty("dateOfEvent")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEvent;

    /**
     * -- GETTER --
     *  Gets start time.
     *
     *
     * -- SETTER --
     *  Sets start time.
     *
     @return the start time
      * @param startTime the start time
     */
    @Setter
    @Column(name = "start_time")
    @JsonProperty("startTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;

    /**
     * -- GETTER --
     *  Gets end time.
     *
     *
     * -- SETTER --
     *  Sets end time.
     *
     @return the end time
      * @param endTime the end time
     */
    @Setter
    @Column(name = "end_time")
    @JsonProperty("endTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime;

    /**
     * -- GETTER --
     *  Gets description.
     *
     *
     * -- SETTER --
     *  Sets description.
     *
     @return the description
      * @param description the description
     */
    @Setter
    @Lob
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    /**
     * -- GETTER --
     *  Gets event.
     *
     *
     * -- SETTER --
     *  Sets event.
     *
     @return the event
      * @param event the event
     */
    @Setter
    @ManyToOne
    @JsonBackReference(value = "event-detail")
    @JoinColumn(name = "event_id",
            foreignKey = @ForeignKey(name = "details_event_fk"))
    private Event event;

    @OneToMany(mappedBy="detail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "detail-performer")
    private final Set<Performer> performers  = new LinkedHashSet<>();

    @OneToMany(mappedBy="detail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "detail-location")
    private final Set<Location> locations  = new LinkedHashSet<>();


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



    public LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(timeString, formatter);
    }

    public LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    public void addLocation(Location location) {
        locations.add(location);
        location.setDetail(this);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
        location.setDetail(null);
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
                ", event=" + (event != null ? event.getId() : "null") +
                '}';
    }
}