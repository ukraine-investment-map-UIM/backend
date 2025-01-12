package com.uim.map.config.web.ports.output;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Builder
@Getter
public class AppException extends RuntimeException {
  private final String reason;
  private final HttpStatusCode status;
}