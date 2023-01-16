package ma.ensa.hospital.repositories;

import ma.ensa.hospital.entities.Consultation;
import ma.ensa.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
