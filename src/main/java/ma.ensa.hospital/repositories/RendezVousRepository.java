package ma.ensa.hospital.repositories;

import ma.ensa.hospital.entities.Patient;
import ma.ensa.hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
}
