package com.uim.map.area.adapter.persistence.entity;

import com.uim.map.area.domain.core.model.AreaStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = AreaEntity.COLLECTION_AREA)
@Data
@Builder
public class AreaEntity {
    public static final String COLLECTION_AREA = "area";

    @Id
    String self;

    @Field("userId")
    String userId;

    @Field("coordinates")
    List<Point> coordinates;

    @Field("layers")
    List<LayerCode> layers;

    @Field("reportId")
    String reportId;

    @Field("status")
    AreaStatus status;

}
