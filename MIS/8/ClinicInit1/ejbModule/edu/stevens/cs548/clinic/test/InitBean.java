package edu.stevens.cs548.clinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.RadiologyTreatment;

import edu.stevens.cs548.clinic.domain.SurgeryTreatment;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientService;
import edu.stevens.cs548.clinic.service.ejb.IProviderService;

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
    
	public static void info(String m) {
		logger.info(m);
	}

	public void TestService() {

	}
	@Inject
	private IPatientService patientService;
	
	@Inject
	private IProviderService providerService;


	@PostConstruct
	private void init(){
		/*
		 * Put your testing logic here. Use the logger to display testing output in the server logs.
		 */
		logger.info("Your name here: Deng Xiong");

		try {
			PatientDtoFactory patientFactory = new PatientDtoFactory();
			ProviderDtoFactory providerFactory = new ProviderDtoFactory();
			TreatmentDtoFactory treatmentFactory = new TreatmentDtoFactory();
			
			PatientDto john = patientFactory.createPatientDto();
			john.setPatientId(12345678L);
			john.setId(1);
			john.setName("John Doe");			
			patientService.addPatient(john);
			
			logger.info("Added "+john.getName()+" with id "+john.getId());;
		
//			ProviderDto russ = providerFactory.createProviderDto();
//			russ.setNpi(23456789);
//			russ.setName("Russel Westbrook");
//			russ.setSpecialization("Radiologist");
//			providerService.addProvider(russ);
//			
//			logger.info("Added "+russ.getName()+" with npi "+russ.getNpi() + "with ProviderType" + russ.getSpecialization());;
			
//			String drugTreatment = "Asthma";
//			TreatmentDto drugtreatment = treatmentFactory.createTreatmentDto(DrugTreatment );
//			russ.setNpi(23456789);
//			russ.setName("Russel Westbrook");
//			russ.setSpecialization("Radiologist");
//			providerService.addProvider(russ);
//			
//			logger.info("Added "+russ.getName()+" with npi "+russ.getNpi() + "with ProviderType" + russ.getSpecialization());;
			
		} catch (Exception e) {

			IllegalStateException ex = new IllegalStateException("Failed to add patient record.");
			ex.initCause(e);
			throw ex;	
		
//		}catch( Exception e2){
//			IllegalStateException ex = new IllegalStateException("Failed to add provider record.");
//			ex.initCause(e2);
//			throw ex;
//		
//		} catch (Exception e3) {
//
//			IllegalStateException ex = new IllegalStateException("Failed to add patient record.");
//			ex.initCause(e);
//			throw ex;	
		}
		
	}

//	private Date setDate(String stringDate) {
//		Date date = null;
//		try {
//			String pattern = "MM/dd/yyyy";
//			SimpleDateFormat format = new SimpleDateFormat(pattern);
//			date = format.parse(stringDate);
//		} catch (Exception e) {
//			IllegalStateException ex = new IllegalStateException("ERROR!!");
//			ex.initCause(e);
//			throw ex;
//		}
//		return date;
//	}
//
}