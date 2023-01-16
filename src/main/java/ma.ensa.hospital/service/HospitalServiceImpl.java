package ma.ensa.hospital.service;

import ma.ensa.hospital.entities.Consultation;
import ma.ensa.hospital.entities.Medecin;
import ma.ensa.hospital.entities.Patient;
import ma.ensa.hospital.entities.RendezVous;
import ma.ensa.hospital.repositories.ConsultationRepository;
import ma.ensa.hospital.repositories.MedecinRepository;
import ma.ensa.hospital.repositories.PatientRepository;
import ma.ensa.hospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service /*les objects de la couche metier utilise cette annotation*/
@Transactional /*je voudrais que toutes les methodes doivent etre transactionnelle */
public class HospitalServiceImpl implements IHospitalService {
    /*injecter soit @autowired ou via le constructeur(la plus conseillee)*/
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());/*Genere une chaine de caract unique car
        elle depend de la date system*/
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
