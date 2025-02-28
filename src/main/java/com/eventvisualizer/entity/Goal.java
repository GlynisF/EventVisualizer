package com.eventvisualizer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "event_id",
    foreignKey = @ForeignKey(name = "goal_event_f"))
    private Goal event;

    @OneToMany(mappedBy = "event")
    private Set<Goal> goals = new LinkedHashSet<>();

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

    public Goal getEvent() {
        return event;
    }

    public void setEvent(Goal event) {
        this.event = event;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

}