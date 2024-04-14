package org.itechciv.dashboard.model;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class PatientAnalyseExcelItem {	
	private String LABNO;	
	private String SAMPLE_STATUS;	
	private String SUBJECTNO;	
	private String STUDY;	
	private String SUBJECTID;	
	private String DRCPT;	
	private String DINTV;	
	private String CODE_SITE;	
	private String NAME_SITE;	
	private String CODE_SITE_DATIM;	
	private String NAME_SITE_DATIM;	
	private String SEX;	
	private String DATEBORN;	
	private String AGEYEARS;	
	private String AGEMONTHS;	
	private String AGEWEEKS;	
	private String VIRAL_LOAD;	
	private String VIRAL_LOAD_LOG;	
	private String TYPE_OF_SAMPLE;	
	private String ANALYSIS_STATUS;	
	private String STARTED_DATE;	
	private String COMPLETED_DATE;	
	private String RELEASED_DATE;	
	private String STATVIH;	
	private String NAMEMED;	
	private String NAMEPRELEV;	
	private String ARV_INIT_DATE;	
	private String ARVREG;	
	private String CURRENT1;	
	private String CURRENT2;	
	private String CURRENT3;	
	private String CURRENT4;	
	private String CURRENT_ART;	
	private String VL_REASON;	
	private String REASON_OTHER;	
	private String INITCD4_COUNT;	
	private String INITCD4_PERCENT;	
	private String INITCD4_DATE;	
	private String DEMANDCD4_COUNT;	
	private String DEMANDCD4_PERCENT;	
	private String DEMANDCD4_DATE;	
	private String PRIOR_VL_BENEFIT;	
	private String VL_PREGNANCY;	
	private String VL_SUCKLE;	
	private String PRIOR_VL_Lab;	
	private String PRIOR_VL_Value;	
	private String PRIOR_VL_Date;	
	private String REPORT_NAME;	
	private String PRINTED_DATE;	
	private String LAST_REPORT_UPDATE;
	

	public PatientAnalyseExcelItem(XSSFRow row) {
		LABNO = getExcelCellValue(row, 0);	
		SAMPLE_STATUS = getExcelCellValue(row, 1);	
		SUBJECTNO = getExcelCellValue(row, 2);	
		STUDY = getExcelCellValue(row, 3);	
		SUBJECTID = getExcelCellValue(row, 4);	
		DRCPT = getExcelCellValue(row, 5);	
		DINTV = getExcelCellValue(row, 6);	
		CODE_SITE = getExcelCellValue(row, 7);	
		NAME_SITE = getExcelCellValue(row, 8);	
		CODE_SITE_DATIM = getExcelCellValue(row, 9);	
		NAME_SITE_DATIM = getExcelCellValue(row, 10);	
		SEX = getExcelCellValue(row, 11);	
		DATEBORN = getExcelCellValue(row, 12);	
		AGEYEARS = getExcelCellValue(row, 13);	
		AGEMONTHS = getExcelCellValue(row, 14);	
		AGEWEEKS = getExcelCellValue(row, 15);	
		VIRAL_LOAD = getExcelCellValue(row, 16);	
		VIRAL_LOAD_LOG = getExcelCellValue(row, 17);	
		TYPE_OF_SAMPLE = getExcelCellValue(row, 18);	
		ANALYSIS_STATUS = getExcelCellValue(row, 19);	
		STARTED_DATE = getExcelCellValue(row, 20);	
		COMPLETED_DATE = getExcelCellValue(row, 21);	
		RELEASED_DATE = getExcelCellValue(row, 22);	
		STATVIH = getExcelCellValue(row, 23);	
		NAMEMED = getExcelCellValue(row, 24);	
		NAMEPRELEV = getExcelCellValue(row, 25);	
		ARV_INIT_DATE = getExcelCellValue(row, 26);	
		ARVREG = getExcelCellValue(row, 27);	
		CURRENT1 = getExcelCellValue(row, 28);	
		CURRENT2 = getExcelCellValue(row, 29);	
		CURRENT3 = getExcelCellValue(row, 30);	
		CURRENT4 = getExcelCellValue(row, 31);	
		CURRENT_ART = getExcelCellValue(row, 32);	
		VL_REASON = getExcelCellValue(row, 33);	
		REASON_OTHER = getExcelCellValue(row, 34);	
		INITCD4_COUNT = getExcelCellValue(row, 35);	
		INITCD4_PERCENT = getExcelCellValue(row, 36);	
		INITCD4_DATE = getExcelCellValue(row, 37);	
		DEMANDCD4_COUNT = getExcelCellValue(row, 38);	
		DEMANDCD4_PERCENT = getExcelCellValue(row, 39);	
		DEMANDCD4_DATE = getExcelCellValue(row, 40);	
		PRIOR_VL_BENEFIT = getExcelCellValue(row, 41);	
		VL_PREGNANCY = getExcelCellValue(row, 42);	
		VL_SUCKLE = getExcelCellValue(row, 43);	
		PRIOR_VL_Lab = getExcelCellValue(row, 44);	
		PRIOR_VL_Value = getExcelCellValue(row, 45);	
		PRIOR_VL_Date = getExcelCellValue(row, 46);	
		REPORT_NAME = getExcelCellValue(row, 47);	
		PRINTED_DATE = getExcelCellValue(row, 48);	
		LAST_REPORT_UPDATE = getExcelCellValue(row, 49);
	}
	
	private String getExcelCellValue(XSSFRow row, int cellIndex)
	{
		XSSFCell cell = row.getCell(cellIndex);
		if (cell == null)
			return null;
		
		cell.setCellType(CellType.STRING);
		try {
			return cell.getStringCellValue();
		} catch(Exception ex) {
			return null;
		}
	}
	
	public String getLABNO() { return LABNO; }
	public void setLABNO(String value) { LABNO = value; }
	public String getSAMPLE_STATUS() { return SAMPLE_STATUS; }
	public void setSAMPLE_STATUS(String value) { SAMPLE_STATUS = value; }
	public String getSUBJECTNO() { return SUBJECTNO; }
	public void setSUBJECTNO(String value) { SUBJECTNO = value; }
	public String getSTUDY() { return STUDY; }
	public void setSTUDY(String value) { STUDY = value; }
	public String getSUBJECTID() { return SUBJECTID; }
	public void setSUBJECTID(String value) { SUBJECTID = value; }
	public String getDRCPT() { return DRCPT; }
	public void setDRCPT(String value) { DRCPT = value; }
	public String getDINTV() { return DINTV; }
	public void setDINTV(String value) { DINTV = value; }
	public String getCODE_SITE() { return CODE_SITE; }
	public void setCODE_SITE(String value) { CODE_SITE = value; }
	public String getNAME_SITE() { return NAME_SITE; }
	public void setNAME_SITE(String value) { NAME_SITE = value; }
	public String getCODE_SITE_DATIM() { return CODE_SITE_DATIM; }
	public void setCODE_SITE_DATIM(String value) { CODE_SITE_DATIM = value; }
	public String getNAME_SITE_DATIM() { return NAME_SITE_DATIM; }
	public void setNAME_SITE_DATIM(String value) { NAME_SITE_DATIM = value; }
	public String getSEX() { return SEX; }
	public void setSEX(String value) { SEX = value; }
	public String getDATEBORN() { return DATEBORN; }
	public void setDATEBORN(String value) { DATEBORN = value; }
	public String getAGEYEARS() { return AGEYEARS; }
	public void setAGEYEARS(String value) { AGEYEARS = value; }
	public String getAGEMONTHS() { return AGEMONTHS; }
	public void setAGEMONTHS(String value) { AGEMONTHS = value; }
	public String getAGEWEEKS() { return AGEWEEKS; }
	public void setAGEWEEKS(String value) { AGEWEEKS = value; }
	public String getVIRAL_LOAD() { return VIRAL_LOAD; }
	public void setVIRAL_LOAD(String value) { VIRAL_LOAD = value; }
	public String getVIRAL_LOAD_LOG() { return VIRAL_LOAD_LOG; }
	public void setVIRAL_LOAD_LOG(String value) { VIRAL_LOAD_LOG = value; }
	public String getTYPE_OF_SAMPLE() { return TYPE_OF_SAMPLE; }
	public void setTYPE_OF_SAMPLE(String value) { TYPE_OF_SAMPLE = value; }
	public String getANALYSIS_STATUS() { return ANALYSIS_STATUS; }
	public void setANALYSIS_STATUS(String value) { ANALYSIS_STATUS = value; }
	public String getSTARTED_DATE() { return STARTED_DATE; }
	public void setSTARTED_DATE(String value) { STARTED_DATE = value; }
	public String getCOMPLETED_DATE() { return COMPLETED_DATE; }
	public void setCOMPLETED_DATE(String value) { COMPLETED_DATE = value; }
	public String getRELEASED_DATE() { return RELEASED_DATE; }
	public void setRELEASED_DATE(String value) { RELEASED_DATE = value; }
	public String getSTATVIH() { return STATVIH; }
	public void setSTATVIH(String value) { STATVIH = value; }
	public String getNAMEMED() { return NAMEMED; }
	public void setNAMEMED(String value) { NAMEMED = value; }
	public String getNAMEPRELEV() { return NAMEPRELEV; }
	public void setNAMEPRELEV(String value) { NAMEPRELEV = value; }
	public String getARV_INIT_DATE() { return ARV_INIT_DATE; }
	public void setARV_INIT_DATE(String value) { ARV_INIT_DATE = value; }
	public String getARVREG() { return ARVREG; }
	public void setARVREG(String value) { ARVREG = value; }
	public String getCURRENT1() { return CURRENT1; }
	public void setCURRENT1(String value) { CURRENT1 = value; }
	public String getCURRENT2() { return CURRENT2; }
	public void setCURRENT2(String value) { CURRENT2 = value; }
	public String getCURRENT3() { return CURRENT3; }
	public void setCURRENT3(String value) { CURRENT3 = value; }
	public String getCURRENT4() { return CURRENT4; }
	public void setCURRENT4(String value) { CURRENT4 = value; }
	public String getCURRENT_ART() { return CURRENT_ART; }
	public void setCURRENT_ART(String value) { CURRENT_ART = value; }
	public String getVL_REASON() { return VL_REASON; }
	public void setVL_REASON(String value) { VL_REASON = value; }
	public String getREASON_OTHER() { return REASON_OTHER; }
	public void setREASON_OTHER(String value) { REASON_OTHER = value; }
	public String getINITCD4_COUNT() { return INITCD4_COUNT; }
	public void setINITCD4_COUNT(String value) { INITCD4_COUNT = value; }
	public String getINITCD4_PERCENT() { return INITCD4_PERCENT; }
	public void setINITCD4_PERCENT(String value) { INITCD4_PERCENT = value; }
	public String getINITCD4_DATE() { return INITCD4_DATE; }
	public void setINITCD4_DATE(String value) { INITCD4_DATE = value; }
	public String getDEMANDCD4_COUNT() { return DEMANDCD4_COUNT; }
	public void setDEMANDCD4_COUNT(String value) { DEMANDCD4_COUNT = value; }
	public String getDEMANDCD4_PERCENT() { return DEMANDCD4_PERCENT; }
	public void setDEMANDCD4_PERCENT(String value) { DEMANDCD4_PERCENT = value; }
	public String getDEMANDCD4_DATE() { return DEMANDCD4_DATE; }
	public void setDEMANDCD4_DATE(String value) { DEMANDCD4_DATE = value; }
	public String getPRIOR_VL_BENEFIT() { return PRIOR_VL_BENEFIT; }
	public void setPRIOR_VL_BENEFIT(String value) { PRIOR_VL_BENEFIT = value; }
	public String getVL_PREGNANCY() { return VL_PREGNANCY; }
	public void setVL_PREGNANCY(String value) { VL_PREGNANCY = value; }
	public String getVL_SUCKLE() { return VL_SUCKLE; }
	public void setVL_SUCKLE(String value) { VL_SUCKLE = value; }
	public String getPRIOR_VL_Lab() { return PRIOR_VL_Lab; }
	public void setPRIOR_VL_Lab(String value) { PRIOR_VL_Lab = value; }
	public String getPRIOR_VL_Value() { return PRIOR_VL_Value; }
	public void setPRIOR_VL_Value(String value) { PRIOR_VL_Value = value; }
	public String getPRIOR_VL_Date() { return PRIOR_VL_Date; }
	public void setPRIOR_VL_Date(String value) { PRIOR_VL_Date = value; }
	public String getREPORT_NAME() { return REPORT_NAME; }
	public void setREPORT_NAME(String value) { REPORT_NAME = value; }
	public String getPRINTED_DATE() { return PRINTED_DATE; }
	public void setPRINTED_DATE(String value) { PRINTED_DATE = value; }
	public String getLAST_REPORT_UPDATE() { return LAST_REPORT_UPDATE; }
	public void setLAST_REPORT_UPDATE(String value) { LAST_REPORT_UPDATE = value; }

}

