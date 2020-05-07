package com.saberking.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.saberking.dao.PatientDao;
import com.saberking.mydatastructure.Singleton;
import com.saberking.pojo.Patient;

public class PatientService {
	private PatientDao patientDao = new PatientDao();

	public String save(Patient patient) {
		Boolean res = patientDao.save(patient);
		if (res) {
			List<Patient> list = patientDao.selectPatient(patient);
			Singleton singleton = Singleton.getSingleton();
			singleton.order(list.get(0));
			return "{\"msg\":\"success\"}";
		} else {
			return "{\"msg\":\"error\"}";
		}
	}

	public String callNumber(String sid, String ptype) {
		Singleton singleton = Singleton.getSingleton();
		Patient patient = singleton.call(sid, ptype);
		if (patient!=null) {
			return JSON.toJSON(patient).toString();
		} else {
			return "{\"msg\":\"isNull\"}";
		}
	}
}
