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
			PatientDto john = patientFactory.createPatientDto();
			john.setPatientId(12345678L);
			john.setName("John Doe");			
			long johnId = patientService.addPatient(john);
			
			logger.info("Added "+john.getName()+" with id "+johnId + "PatientId" + john.getPatientId());;
			
			ProviderDtoFactory providerFactory = new ProviderDtoFactory();
			ProviderDto russ = providerFactory.createProviderDto();
			russ.setNpi(23456789);
			russ.setName("Russel Westbrook");
			russ.setSpecialization("Radiologist");
			long russId = providerService.addProvider(russ);
			
			logger.info("Added "+russ.getName() + "with Id" + russId +" with npi "+russ.getNpi() + "with ProviderType" + russ.getSpecialization());;
			
			TreatmentDtoFactory treatmentdtoFactory = new TreatmentDtoFactory();
			TreatmentFactory treatmentFactory = new TreatmentFactory();
			TreatmentDto treatmentDto;
			Treatment drugTreatment = treatmentFactory.createDrugTreatment("Stomach Ache", "Paracetamol", 2);
			treatmentDto = treatmentdtoFactory.createTreatmentDto((DrugTreatment) drugTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(russId);
			long drugId = providerService.addTreatment(treatmentDto);
			
			logger.info("Provider " + russ.getName() + " is treating patient " + john.getName()
			+ " - Drug Treatment ID : " + drugId);
			
			Treatment surgeryTreatment = treatmentFactory.createSurgery(setDate("04/04/2021"), "Facture");
			treatmentDto = treatmentdtoFactory.createTreatmentDto((SurgeryTreatment) surgeryTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(russId);
			long surgid = providerService.addTreatment(treatmentDto);

			logger.info("Provider " + russ.getName() + " is treating patient " + john.getName()
					+ " - Surgery Treatment ID : " + surgid);

			List<Date> radiologydateList = new ArrayList<Date>();
			for (int i = 1; i < 4; i++) {				
				radiologydateList.add(setDate("04/" + i + "/2021"));				
			}
			Treatment radiologyTreatment = treatmentFactory.createRadiology(radiologydateList, "Lung Cancer");
			treatmentDto = treatmentdtoFactory.createTreatmentDto((RadiologyTreatment) radiologyTreatment);
			treatmentDto.setPatient(johnId);
			treatmentDto.setProvider(russId);
			long rid = providerService.addTreatment(treatmentDto);

			logger.info("Provider " + russ.getName() + " is treating patient " + john.getName()
					+ " - Radiology Treatment ID : " + rid);
		}catch( Exception e2){
			IllegalStateException ex = new IllegalStateException("Failed to add provider record.");
			ex.initCause(e2);
			throw ex;
		
		}
		
		
	}

	private Date setDate(String stringDate) {
		Date date = null;
		try {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(stringDate);
		} catch (Exception e) {
			IllegalStateException ex = new IllegalStateException("ERROR!!");
			ex.initCause(e);
			throw ex;
		}
		return date;
	}
}