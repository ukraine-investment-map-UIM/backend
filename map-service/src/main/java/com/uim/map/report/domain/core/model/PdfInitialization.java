package com.uim.map.report.domain.core.model;

public class PdfInitialization {
    private PdfType type;

    public enum PdfType {
        ONE_PAGE
    }

    public PdfType getType() {
        return type;
    }

    public void setType(PdfType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PdfInitialization{" +
                "type=" + type +
                '}';
    }
}
