package edu.stevens.cs548.clinic.service.dto.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.ProviderSpecType;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;

public class SchemaTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("** Testing with choice element start");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("** Testing with choice element end");
	}

	@Test
	public void test() {
		PatientDtoFactory patientFactory = new PatientDtoFactory();
		ProviderDtoFactory providerFactory = new ProviderDtoFactory();
		TreatmentDtoFactory treatmentFactory = new TreatmentDtoFactory();

	    try {
		    JAXBContext context = JAXBContext.newInstance("edu.stevens.cs548.clinic.service.dto");
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);

		    PatientDto patient = patientFactory.createPatientDto();
		    // TODO fill in the fields
		    patient.setId(10402740);
		    patient.setName("Deng Xiong");
		    patient.setPatientId(3);		    
		    
		    
		    System.out.println();
			marshaller.marshal(patient, System.out);

		    ProviderDto provider = providerFactory.createProviderDto();
		    // TODO fill in the fields
		    provider.setId(10000000);
		    provider.setName("cs548");
		    provider.setNpi("student");
		    ProviderSpecType providerSpecType = ProviderSpecType.SURGERY;
		    
		    provider.setProviderSpec(providerSpecType);
		    
		    System.out.println();
			marshaller.marshal(provider,System.out);
			
			// TODO create different forms of treatment objects and print them
			TreatmentDto treatment = treatmentFactory.createDrugTreatmentDto();
			
			treatment.setDiagnosis("brain-damage");
			
			DrugTreatmentType drugTreatmentType = treatment.getDrugTreatment();
			drugTreatmentType.setDrug("poland spring");
			drugTreatmentType.setDosage(500);
			treatment.setDrugTreatment(drugTreatmentType);
			
			  System.out.println();
				marshaller.marshal(treatment, System.out);
				
			//others
				TreatmentDto surgeryTreatment = treatmentFactory.createSurgeryTreatmentDto();
				
				surgeryTreatment.setDiagnosis("brain-damage");
				
				SurgeryTreatmentType surgeryTreatmentType = surgeryTreatment.getSurgeryTreatment();
				surgeryTreatmentType.setDateOfSurgery("03-01-2021");
				surgeryTreatment.setSurgeryTreatment(surgeryTreatmentType);
				
				  System.out.println();
					marshaller.marshal(surgeryTreatment, System.out);
					
			//radiology
					TreatmentDto radiologyTreatment = treatmentFactory.createRadiologyTreatmentDto();
					
					radiologyTreatment.setDiagnosis("MRI");
					
					RadiologyTreatmentType radiologyTreatmentType = radiologyTreatment.getRadiologyTreatment();
					List<String> dates = new ArrayList();
					dates.add("03-01-2021");
					dates.add("02-28-2021");
					dates.add("02-27-2021");
					radiologyTreatmentType.setDatesOfRadiology(dates);
					radiologyTreatment.setRadiologyTreatment(radiologyTreatmentType);
					  System.out.println();
						marshaller.marshal(radiologyTreatment, System.out);
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
