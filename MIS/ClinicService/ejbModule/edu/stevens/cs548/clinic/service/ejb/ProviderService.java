package edu.stevens.cs548.clinic.service.ejb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.IProviderFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;


/**
 * Session Bean implementation class ProviderService
 */
@RequestScoped
public class ProviderService implements IProviderService {


	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderService.class.getCanonicalName());

	private IProviderFactory providerFactory;
	
	private ProviderDtoFactory providerDtoFactory;

	private IProviderDAO providerDAO;
	
	private IPatientDAO patientDAO;
	
	private TreatmentFactory treatmentFactory;
	
	public ProviderService() {
		// TODO initialize factories
		providerFactory = new ProviderFactory();
		providerDtoFactory = new ProviderDtoFactory();
		treatmentFactory = new TreatmentFactory();
	}
	
	// TODO use dependency injection and lifecycle methods to initialize DAOs
	
	@PersistenceContext(unitName="ClinicDomain")	
	private EntityManager em;
	
	@PostConstruct
	private void initialize(){
		providerDAO = new ProviderDAO(em);
		patientDAO = new PatientDAO(em);
	}
	
	/**
	 * @see IProviderService#addProvider(ProviderDto dto)
	 */
	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		try {
			Provider provider = providerFactory.createProvider(dto.getNpi(), dto.getName(), dto.getSpecialization());
			providerDAO.addProvider(provider);
			return provider.getId();
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	/**
	 * @see IProviderService#getProvider(long)
	 */
	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		try{
			Provider provider = providerDAO.getProvider(id);
			//PatientDto patientDTO = patientToDTO(patient);
			
			return providerDtoFactory.createProviderDto(provider);
		} catch(ProviderExn e){
			throw new ProviderServiceExn(e.getMessage());
		}
	}

	/**
	 * @see IProviderService#getProviderByNPI(long)
	 */
	@Override
	public ProviderDto getProviderByNpi(long pid) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		try{
			Provider provider = providerDAO.getProviderByNPI(pid);
			//PatientDto patientDTO = patientToDTO(patient);
			
			return providerDtoFactory.createProviderDto(provider); 
		} catch(ProviderExn e){
			throw new ProviderServiceExn(e.getMessage());
		}
	}


	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws ProviderNotFoundExn, TreatmentNotFoundExn, ProviderServiceExn {
		// Export treatment DTO from Provider aggregate
		try {
			Provider provider = providerDAO.getProvider(id);
			TreatmentExporter visitor = new TreatmentExporter();
			return provider.exportTreatment(tid, visitor);
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}
 


	@Override
	public long addTreatment(TreatmentDto dto) throws ProviderServiceExn {
		long tid = 0;
		try {
			Provider provider = providerDAO.getProvider(dto.getProvider());
			Patient patient = patientDAO.getPatient(dto.getPatient());
			if (dto.getDrugTreatment() != null) {
				tid = provider.addDrugTreatment(dto.getDiagnosis(), dto.getDrugTreatment().getName(), dto.getDrugTreatment().getDosage(), patient);
			} else if (dto.getRadiology() != null) {
				tid = provider.addRadiology(dto.getRadiology().getDate(), patient, dto.getDiagnosis());
			} else if (dto.getSurgery() != null) {
				tid = provider.addSurgery(dto.getDiagnosis(), dto.getSurgery().getDate(), patient);
			}
		
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		} catch (PatientExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		return tid;
	}



}