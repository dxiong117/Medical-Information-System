package edu.stevens.cs548.clinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.TreatmentDAO;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.service.dto.util.PatientDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());
	// TODO inject an EM
	@PersistenceContext(unitName="ClinicDomain")
	EntityManager em;
	/**
	 * Default constructor.
	 */
	public InitBean() {
	}
    
	@Inject
	IPatientServiceLocal patientService;
	
	@Inject
	IProviderServiceLocal providerService;


	@PostConstruct
	private <T> void init(){
		/*
		 * Put your testing logic here. Use the logger to display testing output in the server logs.
		 */
		logger.info("Your name here: Deng Xiong");

		try {
			
			PatientDtoFactory patientFactory = new PatientDtoFactory();
			ProviderDtoFactory providerFactory = new ProviderDtoFactory();
			TreatmentDtoFactory treatmentFactory = new TreatmentDtoFactory();
			
			/*
			 * Clear the database and populate with fresh data.
			 * 
			 * If we ensure that deletion of patients cascades deletes of treatments,
			 * then we only need to delete patients.
			 */
			
			PatientDto john = patientFactory.createPatientDto();
			john.setPatientId(12345678L);
			john.setName("John Doe");
			
			patientService.addPatient(john);
			
			logger.info("Added "+john.getName()+" with id "+john.getId());;
			
			//
			ProviderDto Ray = providerFactory.createProviderDto();
			Ray.setId(5454);
			Ray.setName("Ray Dan");
			Ray.setNpi(123456);
					
			providerService.addProvider(Ray);
			
			logger.info("Added Provider: "+Ray.getName()+" with npi "+Ray.getNpi());
			logger.info("Added "+Ray.getName()+" with id "+Ray.getId());;
			
			// TODO add more testing, including treatments and providers
			//provider
			ProviderDto russel = providerFactory.createProviderDto();
			russel.setId(9898);
			russel.setName("Russel Westbrook");
			russel.setSpecialization("Radiologist");
			
			providerService.addProvider(russel);
			
			logger.info("Added Provider: "+russel.getName()+" with npi "+russel.getNpi());
			
			logger.info("added provider"+russel.getName() + "with id"+russel.getId());
			
//			//Adding Surgery 
//			long surgeryid = Ray.addSurgeryTreatment(setDate("05/05/2010"), "appendics", john);
//
//			//Adding Radiology Treatment 
//			List<Date> radiologyDates = new ArrayList<Date>();
//			for(int i =1;i<4;i++){
//				radiologyDates.add(setDate("10/"+i+"/2021"));
//			}
//			long rid = russel.addRadiologyTreatment(radiologyDates, "Cancer", john);
//			
//			logger.info("Provider : " + russel.getName() + " supervising treatment with id " + rid + " for patient "
//					+ john.getName());
//			
//			//Adding Drug Treatment 
//			long drugTreatmentid = Ray.addDrugTreatment("Asthma", "Epitomic", 6, john);
//			
//			logger.info("Provider : " + Ray.getName() + " supervising treatment with id " + drugTreatmentid + " for patient "
//					+ john.getName());
//			
		} catch (Exception e) {

			// logger.log(Level.SEVERE, "Failed to add patient record.", e);
			IllegalStateException ex = new IllegalStateException("Failed to add patient record.", e);
			throw ex;
			
		} 
		
	}
		
		
}