package com.eventvisualizer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity (name = "Performer")
@Table (name = "performer")
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private int id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "moniker", length = 100)
    private String moniker;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "performance_fee", precision = 10, scale = 2)
    private BigDecimal performanceFee;

    @ManyToOne
    @JsonBackReference(value = "detail-performer")
    @JoinColumn(name = "detail_id",
            foreignKey = @ForeignKey(name = "performer_detail_fk"))
    private Detail detail;

    public Performer() {
    }

    public Performer(String fullName, String moniker, String email, BigDecimal performanceFee) {
        this.fullName = fullName;
        this.moniker = moniker;
        this.email = email;
        this.performanceFee = performanceFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMoniker() {
        return moniker;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPerformanceFee() {
        return performanceFee;
    }

    public void setPerformanceFee(BigDecimal performanceFee) {
        this.performanceFee = performanceFee;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Performer performer = (Performer) o;
        return Objects.equals(id, performer.id) && Objects.equals(fullName, performer.fullName) && Objects.equals(moniker, performer.moniker) && Objects.equals(email, performer.email) && Objects.equals(performanceFee, performer.performanceFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, moniker, email, performanceFee);
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", moniker='" + moniker + '\'' +
                ", email='" + email + '\'' +
                ", performanceFee=" + performanceFee +
                ", detail=" + (detail != null ? detail.getId() : "null") +
                '}';
    }
}