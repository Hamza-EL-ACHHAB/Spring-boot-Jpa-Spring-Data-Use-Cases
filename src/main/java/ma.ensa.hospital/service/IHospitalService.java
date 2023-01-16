package ma.ensa.hospital.service;

import ma.ensa.hospital.entities.Consultation;
import ma.ensa.hospital.entities.Medecin;
import ma.ensa.hospital.entities.Patient;
import ma.ensa.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
