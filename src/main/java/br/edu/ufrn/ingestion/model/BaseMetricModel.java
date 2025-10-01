package br.edu.ufrn.ingestion.model;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

public abstract class BaseMetricModel {
    @PrimaryKeyColumn(name = "patient_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int patientId;

    @PrimaryKeyColumn(name = "timestamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime timestamp;

    public BaseMetricModel() {}

    public BaseMetricModel(int patientId, LocalDateTime timestamp) {
        this.patientId = patientId;
        this.timestamp = timestamp;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}