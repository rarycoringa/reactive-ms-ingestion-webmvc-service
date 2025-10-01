package br.edu.ufrn.ingestion.record.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.edu.ufrn.ingestion.record.OxygenSaturation;

public record OxygenSaturationResponse(
    @JsonProperty("patient_id") int patientId,
    @JsonProperty("timestamp") @JsonFormat(shape = JsonFormat.Shape.STRING) LocalDateTime timestamp,
    @JsonProperty("oxygen_saturation") OxygenSaturation oxygenSaturation
) {}
