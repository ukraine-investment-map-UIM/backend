package com.uim.map.area.integration.model;

import com.uim.map.area.integration.model.enums.AreaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document(collation = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaEntity {
    @Id
    UUID self;

    @Field("userId")
    UUID userId;

    @Field("coordinates")
    List<Point> coordinates;

    @Field("layers")
    List<LayerCode> layers;

    @Field("reportId")
    UUID reportId;

    @Field("status")
    AreaStatus status;

}
