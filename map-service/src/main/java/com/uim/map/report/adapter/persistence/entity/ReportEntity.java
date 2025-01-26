package com.uim.map.report.adapter.persistence.entity;

import com.uim.map.report.domain.core.model.ReportStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = ReportEntity.REPORT_COLLECTION)
@Builder
public class ReportEntity {
    public static final String REPORT_COLLECTION = "report";

    @Id
    String self;
    @Field(name = "areas")
    List<AreaCode> areas;
    @Field(name = "userId")
    String userId;
    @Field(name = "url")
    String url;
    @Field(name = "status")
    ReportStatus status;
}
