package com.ihbaby.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by qiang on 2017/03/02.
 */
public class RecordForm {
    private Long id;

    private Long userId;

    private Long hospitalId;

    private Long doctorId;

    private String doctorSays;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    private String sysCode;

    private String diagnosisResult;

    private String inspectionResults;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorSays() {
        return doctorSays;
    }

    public void setDoctorSays(String doctorSays) {
        this.doctorSays = doctorSays;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getInspectionResults() {
        return inspectionResults;
    }

    public void setInspectionResults(String inspectionResults) {
        this.inspectionResults = inspectionResults;
    }

}
