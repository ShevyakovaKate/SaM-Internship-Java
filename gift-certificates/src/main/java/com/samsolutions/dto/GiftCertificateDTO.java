package com.samsolutions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class GiftCertificateDTO implements Serializable {
    private static final long serialVersionUID = -3404573318477483112L;
    private Long id;
    private String name;
    private String description;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfCreation;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dateOfModification;
    private Integer duration;
    private Set<GiftTagDTO> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(LocalDate dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<GiftTagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<GiftTagDTO> tags) {
        this.tags = tags;
    }

}
