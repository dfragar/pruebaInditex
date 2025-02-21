package com.inditex.priceservice.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponse {

    @Schema(description = "API path invoked by client", example = "/api/prices/apply-price")
    private String apiPath;

    @Schema(description = "Error code representing the error that occurred", example = "404")
    private HttpStatus errorCode;

    @Schema(description = "Error message describing the details of the error",
            example = "Price not found with the given input data")
    private String errorMessage;

    @Schema(description = "Timestamp when the error occurred", example = "2023-05-15T10:00:00")
    private LocalDateTime errorTime;

}
