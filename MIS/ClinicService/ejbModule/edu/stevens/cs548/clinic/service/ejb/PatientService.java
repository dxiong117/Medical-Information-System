package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IPatientFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;

/**
 * Session Bean implementation class PatientService
 */
@RequestScoped
public class PatientService implements IPatientService {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientService.class.getCanonicalName());

	private IPatientFactory patientFactory;
	
	private PatientDtoFactory patientDtoFactory;

	private IPatientDAO patientDAO;

	/**
	 * Default constructor.
	 */
	public PatientService() {
		// TODO initialize factories
		patientFactory = new PatientFactory();
		patientDtoFactory = new PatientDtoFactory(); 
	}
	
	// TODO use dependency injection and lifecycle methods to initialize DAOs
	@PersistenceContext(unitName="ClinicDomain")
	private EntityManager em;
	
	@PostConstruct
	private void initialize(){
		patientDAO = new PatientDAO(em);
		patientDtoFactory = new PatientDtoFactory();
	}
	

	/**
	 * @see IPatientService#addPatient(String, Date, long)
	 */
	@Override
	public long addPatient(PatientDto dto) throws PatientServiceExn {
		// Use factory to create patient entity, and persist with DAO
		try {			
			Patient patient = patientFactory.createPatient(dto.getPatientId(), dto.getName());			
			patientDAO.addPatient(patient);			
			return patient.getId();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	@Override
	public PatientDto getPatient(long id) throws PatientServiceExn {
		// TODO use DAO to get patient by database key
		
		try{						
			Patient patient = patientDAO.getPatient(id);			
			PatientDto dto = patientDtoFactory.createPatientDto(patient);
			return dto;
			
		} catch(PatientExn e){			
			throw new PatientServiceExn(e.toString());
		}		
		
	}


	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn {
		// TODO use DAO to get patient by patient id
		try {
			Patient patient = patientDAO.getPatientByPatientId(pid);
			PatientDto dto = patientDtoFactory.createPatientDto(patient);
			return dto;

		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}
	
		
	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// Export treatment DTO from patient aggregate
		try {			
			Patient patient = patientDAO.getPatient(id);
			TreatmentExporter visitor = new TreatmentExporter();
			return patient.exportTreatment(tid, visitor);			
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}


	
}