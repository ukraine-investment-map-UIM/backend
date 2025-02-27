package com.uim.api.report.domain.core.model;

import java.util.Objects;

public class Layer {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Layer layer = (Layer) o;
        return Objects.equals(code, layer.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Layer{" +
                "code='" + code + '\'' +
                '}';
    }
}
