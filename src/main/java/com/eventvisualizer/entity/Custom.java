package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.Objects;

@Entity (name = "Custom")
@Table(name = "custom", schema = "ev_test")
public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int id;

    @Column(name = "custom_field")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> customField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",
    foreignKey = @ForeignKey(name = "custom_event_fk"))
    @JsonBackReference(value = "event-custom")
    private Event event;

    public Custom() {
    }

    public Custom(Map<String, Object> customField) {
        this.customField = customField;
    }

    public int getId() {
        return id;
    }

    public Map<String, Object> getCustomField() {
        return customField;
    }

    public void setCustomField(Map<String, Object> customField) {
        this.customField = customField;
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
        Custom custom = (Custom) o;
        return id == custom.id && Objects.equals(customField, custom.customField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customField);
    }

    @Override
    public String toString() {
        return "Custom{" +
                "customField=" + customField +
                ", id=" + id +
                ", event=" + (event != null ? event.getId() : "null") +
                '}';
    }
}