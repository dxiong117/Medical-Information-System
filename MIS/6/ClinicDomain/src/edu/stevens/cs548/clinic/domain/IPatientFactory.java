package edu.stevens.cs548.clinic.domain;



public interface IPatientFactory {
	
	/**
	 * "age" field is sanity check: compare with "dob."
	 */
	public Patient createPatient (long pid, String name) throws IPatientDAO.PatientExn;

}