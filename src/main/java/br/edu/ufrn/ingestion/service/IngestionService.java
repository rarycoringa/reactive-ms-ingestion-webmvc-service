package br.edu.ufrn.ingestion.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        LocalDateTime timestamp = LocalDateTime
            .now()
            .truncatedTo(ChronoUnit.SECONDS);
        
        BloodPressureModel bloodPressureModel = new BloodPressureModel(
            patientId,
            timestamp,
            bloodPressure.systolicValue(),
            bloodPressure.diastolicValue()
        );

        bloodPressureModel = bloodPressureRepository.save(bloodPressureModel);

        return new BloodPressureResponse(
            bloodPressureModel.getPatientId(),
            bloodPressureModel.getTimestamp(),
            bloodPressure
        );
    }

    public List<BloodPressureResponse> retrieveBloodPressure(int patientId, LocalDateTime start, LocalDateTime end) {
        List<BloodPressureModel> bloodPressureModelList = bloodPressureRepository.findByPatientIdAndTimestampBetween(
            patientId, start, end
        );
        
        return bloodPressureModelList
            .stream()
            .map(model -> new BloodPressureResponse(
                model.getPatientId(),
                model.getTimestamp(),
                new BloodPressure(model.getSystolicValue(), model.getDiastolicValue())
            ))
            .collect(Collectors.toList());
    }

    public HeartRateResponse createHeartRate(int patientId, HeartRate heartRate) {
        LocalDateTime timestamp = LocalDateTime
            .now()
            .truncatedTo(ChronoUnit.SECONDS);
        
        HeartRateModel heartRateModel = new HeartRateModel(
            patientId,
            timestamp,
            heartRate.value()
        );

        heartRateModel = heartRateRepository.save(heartRateModel);

        return new HeartRateResponse(
            heartRateModel.getPatientId(),
            heartRateModel.getTimestamp(),
            heartRate
        );
    }

    public List<HeartRateResponse> retrieveHeartRate(int patientId, LocalDateTime start, LocalDateTime end) {
        List<HeartRateModel> heartRateModelList = heartRateRepository.findByPatientIdAndTimestampBetween(
            patientId, start, end
        );
        
        return heartRateModelList
            .stream()
            .map(model -> new HeartRateResponse(
                model.getPatientId(),
                model.getTimestamp(),
                new HeartRate(model.getValue())
            ))
            .collect(Collectors.toList());
    }

    public OxygenSaturationResponse createOxygenSaturation(int patientId, OxygenSaturation oxygenSaturation) {
        LocalDateTime timestamp = LocalDateTime
            .now()
            .truncatedTo(ChronoUnit.SECONDS);
        
        OxygenSaturationModel oxygenSaturationModel = new OxygenSaturationModel(
            patientId,
            timestamp,
            oxygenSaturation.value()
        );

        oxygenSaturationModel = oxygenSaturationRepository.save(oxygenSaturationModel);

        return new OxygenSaturationResponse(
            oxygenSaturationModel.getPatientId(),
            oxygenSaturationModel.getTimestamp(),
            oxygenSaturation
        );
    }

    public List<OxygenSaturationResponse> retrieveOxygenSaturation(int patientId, LocalDateTime start, LocalDateTime end) {
        List<OxygenSaturationModel> oxygenSaturationModelList = oxygenSaturationRepository.findByPatientIdAndTimestampBetween(
            patientId, start, end
        );
        
        return oxygenSaturationModelList
            .stream()
            .map(model -> new OxygenSaturationResponse(
                model.getPatientId(),
                model.getTimestamp(),
                new OxygenSaturation(model.getValue())
            ))
            .collect(Collectors.toList());
    }

}
