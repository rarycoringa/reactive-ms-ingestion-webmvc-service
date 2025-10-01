package br.edu.ufrn.ingestion.model;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("blood_pressure")
public class BloodPressureModel extends BaseMetricModel {

    @Column("systolic_value")
    private int systolicValue;

    @Column("diastolic_value")
    private int diastolicValue;

    public BloodPressureModel() {}

    public BloodPressureModel(int patientId, LocalDateTime timestamp, int systolicValue, int diastolicValue) {
        super(patientId, timestamp);
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
    }

    public int getSystolicValue() {
        return systolicValue;
    }

    public void setSystolicValue(int systolicValue) {
        this.systolicValue = systolicValue;
    }

    public int getDiastolicValue() {
        return diastolicValue;
    }

    public void setDiastolicValue(int diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

}
