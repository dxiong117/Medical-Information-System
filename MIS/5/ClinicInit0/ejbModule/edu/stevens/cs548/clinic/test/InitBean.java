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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.Provider.ProviderType;
import edu.stevens.cs548.clinic.domain.TreatmentDAO;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.RadiologyTreatment;
import edu.stevens.cs548.clinic.domain.Treatment;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());

	/**
	 * Default constructor.
	 */
	public InitBean() {
	}
	
	// TODO inject an EM
	@PersistenceContext(unitName="ClinicDomain")
	EntityManager em;

	@PostConstruct
	private <T> void init() {
		/*
		 * Put your testing logic here. Use the logger to display testing output in the server logs.
		 */
		logger.info("Your name here: Deng Xiong");

		try {

			
			IPatientDAO patientDAO = new PatientDAO(em);
			ITreatmentDAO treatmentDAO = new TreatmentDAO(em);
			IProviderDAO providerDAO = new ProviderDAO(em);
			
			PatientFactory patientFactory = new PatientFactory();
			TreatmentFactory treatmentFactory = new TreatmentFactory();
			ProviderFactory providerFactory = new ProviderFactory();							
			
			Patient jj = patientFactory.createPatient(12345678L, "JJ Lin");
			
			patientDAO.addPatient(jj);
			
			logger.info("Added "+jj.getName()+" with id "+jj.getId());
			
			Patient p = patientDAO.getPatient(jj.getId());
			
			logger.info("Added Patient "+p.getName()+" with id "+p.getId() +" & Patient Id: "+p.getPatientId());;
			
			Patient p1 = patientDAO.getPatientByPatientId(p.getPatientId());
			
			logger.info("Added Patientby ID: "+p1.getName()+" with id "+p1.getId());
		
			Provider russel = providerFactory.createProvider(9898, "Russel Westbrook", ProviderType.RADIOLOGIST);
			
			providerDAO.addProvider(russel);
			
			logger.info("Added Provider: "+russel.getName()+" with npi "+russel.getNpi());
			
			logger.info("added provider"+russel.getName() + "with id"+russel.getId());
			
			Patient Dan = patientFactory.createPatient(15935785L, "Dan B");
			patientDAO.addPatient(Dan);
			
			logger.info("Added "+Dan.getName()+" with id "+Dan.getId());

			
			Provider Ricky = providerFactory.createProvider(65983241, "Wayne Ricky", ProviderType.SURGEON);
			providerDAO.addProvider(Ricky);
			
			//Adding Surgery 
			long surgeryid = Ricky.addSurgeryTreatment(setDate("05/05/2021"), "appendics", jj);
			
			//Adding Radiology Treatment 
			List<Date> radiologyDates = new ArrayList<Date>();
			
			for(int i =1;i<4;i++){
				radiologyDates.add(setDate("10/"+i+"/2021"));
			}
			
			long rid = russel.addRadiologyTreatment(radiologyDates, "Cancer", Dan);
			
			logger.info("Provider : " + russel.getName() + " supervising treatment with id " + rid + " for patient "
					+ Dan.getName());
			
			//Adding Drug Treatment 
			long drugTreatmentid = Ricky.addDrugTreatment("Asthma", "Epitomic", 6, Dan);
			
			logger.info("Provider : " + Ricky.getName() + " supervising treatment with id " + drugTreatmentid + " for patient "
					+ Dan.getName());
			
			
			
			
		} catch (PatientExn  e) {

			// logger.log(Level.SEVERE, "Failed to add patient record.", e);
			IllegalStateException ex = new IllegalStateException("patient Exception" + e.getMessage());
			ex.initCause(e);
			throw ex;
			
		} 
		catch( ProviderExn e2){
			IllegalStateException ex = new IllegalStateException("provider Exception :"  + e2.getMessage());
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
			IllegalStateException ex = new IllegalStateException("Error while setting state");
			ex.initCause(e);
			throw ex;
		}
		return date;
	}
	
}