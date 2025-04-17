package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity (name = "Goal")
@Table(name = "goal")
public class Goal {

    @Id
    @Column(name = "event_id")
    private Integer eventId;

    @Lob
    @Column(name = "goal_description")
    @JsonProperty("goalDescription")
    private String goalDescription;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Event event;


    public Goal() {
    }

    public Goal(String goalDescription) {
        this.goalDescription = goalDescription;

    }

    public Goal newGoalHelper(Goal goal) {
        return new Goal(goal.getGoalDescription());
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
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
        Goal goal = (Goal) o;
        return Objects.equals(eventId, goal.eventId) && Objects.equals(goalDescription, goal.goalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, goalDescription);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "eventId=" + eventId +
                ", goalDescription='" + goalDescription + '\'' +
                '}';
    }
}