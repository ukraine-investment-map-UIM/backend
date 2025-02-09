package com.uim.map.layer.adapter.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = LayerEntity.LAYER_COLLECTION)
@Data
@Builder
public class LayerEntity {
    public static final String LAYER_COLLECTION = "layer";

    @Id
    String self;

    @Field("name")
    String name;

    @Field("description")
    String description;

    @Field("coordinates")
    List<Point> coordinates;

    @Field("intensity")
    Double intensity;
}
