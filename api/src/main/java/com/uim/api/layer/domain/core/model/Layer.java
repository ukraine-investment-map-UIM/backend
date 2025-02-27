package com.uim.api.layer.domain.core.model;

import com.uim.api.infrastructure.calculation.domain.core.model.Point;

import java.util.List;
import java.util.Objects;

public class Layer {
    private String self;
    private String name;
    private String description; // description of the intensity field
    private List<Point> coordinates;
    private Double intensity; // from (0.0 to 1.0)

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Layer layer = (Layer) o;
        return Objects.equals(self, layer.self);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(self);
    }

    public Layer() {
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "self='" + self + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", coordinates=" + coordinates +
                ", intensity=" + intensity +
                '}';
    }
}
