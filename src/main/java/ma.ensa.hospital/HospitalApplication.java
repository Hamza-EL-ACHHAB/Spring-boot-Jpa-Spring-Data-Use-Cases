package ma.ensa.hospital;

import ma.ensa.hospital.entities.*;
import ma.ensa.hospital.repositories.ConsultationRepository;
import ma.ensa.hospital.repositories.MedecinRepository;
import ma.ensa.hospital.repositories.PatientRepository;
import ma.ensa.hospital.repositories.RendezVousRepository;
import ma.ensa.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean /*avec cette annotation la methode va s'executer au demmarage et il va retourner un objet
	qui va devenir un composant spring(c'est comme creer une classe avec @Component)
	c'est bcq mieux d'implentez l'interface CommandLineRunner => si on veut injecter qlq chose
	il suffit de le declarer parmi les argument de la methode start (sans attribuer autowired)*/
	CommandLineRunner start(IHospitalService hospitalService,PatientRepository patientRepository
			,RendezVousRepository rendezVousRepository,MedecinRepository medecinRepository){/*Spring va injecter
	les interface Repository dans le programme principale*/
		return args -> {
			Stream.of("Hamza","Ayoub","Hamada")
					.forEach(name -> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("Med1","Med2","Med3")
					.forEach(name -> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});
			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Hamza");
			Medecin medecin = medecinRepository.findByNom("Med1");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRV = hospitalService.saveRendezVous(rendezVous);
			System.out.println(savedRV.getId());

			RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(rendezVous1.getDate());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultatioon");
			hospitalService.saveConsultation(consultation);


		};
	}

}
