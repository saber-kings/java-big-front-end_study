package com.saberking.dao;

import java.util.List;

import com.saberking.pojo.Patient;
import com.saberking.utils.BaseDao;

public class PatientDao {
	public Boolean save(Patient patient) {
		String sql = "insert into t_patient(pname,age,sex,sid,ptype) values(?,?,?,?,?)";
		int num = BaseDao.update(sql, patient.getPname(), patient.getAge(), patient.getSex(), patient.getSid(), patient.getPtype());
		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Patient> selectPatient(Patient patient) {
		String sql = "select * from t_patient where pname=? and age=? and sid=? and sex=? and ptype=?";
		return BaseDao.getall(sql, Patient.class, patient.getPname(), patient.getAge(), patient.getSid(), patient.getSex(), patient.getPtype());
	}
}
