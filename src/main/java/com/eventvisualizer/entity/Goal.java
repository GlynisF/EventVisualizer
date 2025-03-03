package com.eventvisualizer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity (name = "Goal")
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @Lob
    @Column(name = "goal_description")
    private String goalDescription;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    public Goal() {
    }

    public Goal(String goalDescription) {
        this.goalDescription = goalDescription;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, goal.id) && Objects.equals(goalDescription, goal.goalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goalDescription);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", goalDescription='" + goalDescription + '\'' +
                ", eventId=" + (event != null ? event.getId() : "null") +
                '}';
    }
}