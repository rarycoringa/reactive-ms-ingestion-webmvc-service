package br.edu.ufrn.ingestion.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.ingestion.model.BloodPressureModel;
import br.edu.ufrn.ingestion.model.HeartRateModel;
import br.edu.ufrn.ingestion.model.OxygenSaturationModel;
import br.edu.ufrn.ingestion.record.BloodPressure;
import br.edu.ufrn.ingestion.record.HeartRate;
import br.edu.ufrn.ingestion.record.OxygenSaturation;
import br.edu.ufrn.ingestion.record.response.BloodPressureResponse;
import br.edu.ufrn.ingestion.record.response.HeartRateResponse;
import br.edu.ufrn.ingestion.record.response.OxygenSaturationResponse;
import br.edu.ufrn.ingestion.repository.BloodPressureRepository;
import br.edu.ufrn.ingestion.repository.HeartRateRepository;
import br.edu.ufrn.ingestion.repository.OxygenSaturationRepository;

@Service
public class IngestionService {

    @Autowired
    private BloodPressureRepository bloodPressureRepository;

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private OxygenSaturationRepository oxygenSaturationRepository;

    public BloodPressureResponse createBloodPressure(int patientId, BloodPressure bloodPressure) {
        BloodPressureModel bloodPressureModel = new BloodPressureModel(
            patientId, Instant.now(), bloodPressure.systolicValue(), bloodPressure.diastolicValue()
        );

        bloodPressureModel = bloodPressureRepository.save(bloodPressureModel);

        BloodPressureResponse bloodPressureResponse = new BloodPressureResponse(
            bloodPressureModel.getPatientId(), bloodPressureModel.getRegisteredAt(), bloodPressure
        );

        return bloodPressureResponse;
    }

    public List<BloodPressureResponse> retrieveBloodPressure(int patientId, LocalDateTime start, LocalDateTime end) {
        Instant startAsInstant = start.atZone(ZoneOffset.UTC).toInstant();
        Instant endAsInstant = end.atZone(ZoneOffset.UTC).toInstant();
        
        List<BloodPressureModel> bloodPressureModelList = bloodPressureRepository.findByPatientIdAndRegisteredAtBetween(
            patientId, startAsInstant, endAsInstant
        );
        
        List<BloodPressureResponse> bloodPressureList = bloodPressureModelList.stream()
            .map(model -> new BloodPressureResponse(
                model.getPatientId(),
                model.getRegisteredAt(),
                new BloodPressure(model.getSystolicValue(), model.getDiastolicValue())
            ))
            .collect(Collectors.toList());
        
        return bloodPressureList;
    }

    public HeartRateResponse createHeartRate(int patientId, HeartRate heartRate) {
        HeartRateModel heartRateModel = new HeartRateModel(
            patientId, Instant.now(), heartRate.value()
        );

        heartRateModel = heartRateRepository.save(heartRateModel);

        HeartRateResponse heartRateResponse = new HeartRateResponse(
            heartRateModel.getPatientId(), heartRateModel.getRegisteredAt(), heartRate
        );

        return heartRateResponse;
    }

    public List<HeartRateResponse> retrieveHeartRate(int patientId, LocalDateTime start, LocalDateTime end) {
        Instant startAsInstant = start.atZone(ZoneOffset.UTC).toInstant();
        Instant endAsInstant = end.atZone(ZoneOffset.UTC).toInstant();
        
        List<HeartRateModel> heartRateModelList = heartRateRepository.findByPatientIdAndRegisteredAtBetween(
            patientId, startAsInstant, endAsInstant
        );
        
        List<HeartRateResponse> heartRateList = heartRateModelList.stream()
            .map(model -> new HeartRateResponse(
                model.getPatientId(),
                model.getRegisteredAt(),
                new HeartRate(model.getValue())
            ))
            .collect(Collectors.toList());
        
        return heartRateList;
    }

    public OxygenSaturationResponse createOxygenSaturation(int patientId, OxygenSaturation oxygenSaturation) {
        OxygenSaturationModel oxygenSaturationModel = new OxygenSaturationModel(
            patientId, Instant.now(), oxygenSaturation.value()
        );

        oxygenSaturationModel = oxygenSaturationRepository.save(oxygenSaturationModel);

        OxygenSaturationResponse oxygenSaturationResponse = new OxygenSaturationResponse(
            oxygenSaturationModel.getPatientId(), oxygenSaturationModel.getRegisteredAt(), oxygenSaturation
        );

        return oxygenSaturationResponse;
    }

    public List<OxygenSaturationResponse> retrieveOxygenSaturation(int patientId, LocalDateTime start, LocalDateTime end) {
        Instant startAsInstant = start.atZone(ZoneOffset.UTC).toInstant();
        Instant endAsInstant = end.atZone(ZoneOffset.UTC).toInstant();
        
        List<OxygenSaturationModel> oxygenSaturationModelList = oxygenSaturationRepository.findByPatientIdAndRegisteredAtBetween(
            patientId, startAsInstant, endAsInstant
        );
        
        List<OxygenSaturationResponse> oxygenSaturationList = oxygenSaturationModelList.stream()
            .map(model -> new OxygenSaturationResponse(
                model.getPatientId(),
                model.getRegisteredAt(),
                new OxygenSaturation(model.getValue())
            ))
            .collect(Collectors.toList());
        
        return oxygenSaturationList;
    }

}
