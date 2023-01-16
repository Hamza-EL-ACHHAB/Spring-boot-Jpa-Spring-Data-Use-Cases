package ma.ensa.hospital.repositories;

import ma.ensa.hospital.entities.Medecin;
import ma.ensa.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
}
