package br.edu.ufrn.ingestion.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.ingestion.record.BloodPressure;
import br.edu.ufrn.ingestion.record.HeartRate;
import br.edu.ufrn.ingestion.record.OxygenSaturation;
import br.edu.ufrn.ingestion.record.response.BloodPressureResponse;
import br.edu.ufrn.ingestion.record.response.HeartRateResponse;
import br.edu.ufrn.ingestion.record.response.OxygenSaturationResponse;
import br.edu.ufrn.ingestion.service.IngestionService;

@RestController
public class IngestionRestAPIController {

    @Autowired
    private IngestionService ingestionService;

    @PostMapping("{id}/pressure")
    public ResponseEntity<BloodPressureResponse> createBloodPressure(
        @PathVariable("id") int patientId,
        @RequestBody BloodPressure bloodPressure
    ) {

        BloodPressureResponse response = ingestionService.createBloodPressure(
            patientId, bloodPressure
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("{id}/pressure")
    public ResponseEntity<List<BloodPressureResponse>> retrieveBloodPressure(
        @PathVariable("id") int patientId,
        @RequestParam LocalDateTime start,
        @RequestParam LocalDateTime end
    ) {

        List<BloodPressureResponse> response = ingestionService.retrieveBloodPressure(
            patientId, start, end
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PostMapping("{id}/heart")
    public ResponseEntity<HeartRateResponse> createHeartRate(
        @PathVariable("id") int patientId,
        @RequestBody HeartRate heartRate
    ) {

        HeartRateResponse response = ingestionService.createHeartRate(
            patientId, heartRate
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("{id}/heart")
    public ResponseEntity<List<HeartRateResponse>> retrieveHeartRate(
        @PathVariable("id") int patientId,
        @RequestParam LocalDateTime start,
        @RequestParam LocalDateTime end
    ) {

        List<HeartRateResponse> response = ingestionService.retrieveHeartRate(
            patientId, start, end
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PostMapping("{id}/saturation")
    public ResponseEntity<OxygenSaturationResponse> createOxygenSaturation(
        @PathVariable("id") int patientId,
        @RequestBody OxygenSaturation oxygenSaturation
    ) {

        OxygenSaturationResponse response = ingestionService.createOxygenSaturation(
            patientId, oxygenSaturation
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("{id}/saturation")
    public ResponseEntity<List<OxygenSaturationResponse>> retrieveOxygenSaturation(
        @PathVariable("id") int patientId,
        @RequestParam LocalDateTime start,
        @RequestParam LocalDateTime end
    ) {

        List<OxygenSaturationResponse> response = ingestionService.retrieveOxygenSaturation(
            patientId, start, end
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

}
