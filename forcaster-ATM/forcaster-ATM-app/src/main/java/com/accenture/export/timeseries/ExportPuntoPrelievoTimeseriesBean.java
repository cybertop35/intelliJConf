package com.accenture.export.timeseries;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.i4c.antares.dotnotation.datatypes.FEArray;
import com.i4c.antares.dotnotation.datatypes.FEDataType;
import com.i4c.antares.dotnotation.datatypes.FEInstance;
import com.google.common.collect.Table.Cell;
import com.i4c.antares.common.LevelLoadDTO;
import com.i4c.antares.common.core.PropertiesUtil;
import com.i4c.antares.common.exceptions.E4CEntityException;
import com.i4c.antares.common.exceptions.MissingDateException;
import com.i4c.antares.common.model.GranularityType;
import com.i4c.antares.common.model.timeseries.TimeSeries;
import com.i4c.antares.common.model.timeseries.TimeUtil;
import com.i4c.antares.common.to.EntityFieldValueTO;
import com.i4c.antares.common.to.EntityTo;
import com.i4c.antares.common.to.TimeSeriesTO;
import com.i4c.antares.ejb.EntityFacadeLocal;
import com.i4c.antares.folderloader.FileUtil;
import com.i4c.antares.formulaeditor.business.dto.FormulaResultDTO;
import com.i4c.antares.formulaeditor.ejb.facade.FormulaEditorFacadeLocal;
import com.i4c.antares.formulaeditor.exception.FormulaEditorException;
import com.i4c.antares.logging.facade.LoggingFacadePublicLocal;
import com.i4c.antares.timeseries.ejb.TimeSeriesFacadeLocal;
import com.i4c.antares.usersactions.ejb.UserFacadeLocal;
import com.i4c.antares.usersactions.to.UserTO;
import com.i4c.commons.app.entity.session.IEntityBeanLocal;
import com.i4c.commons.app.util.LogParam;
import com.i4c.commons.app.util.LoggingUtils;

@Stateless
public class ExportPuntoPrelievoTimeseriesBean implements ExportPuntoPrelievoTimeseriesLocal,ExportPuntoPrelievoTimeseriesRemote {
	 private static Logger log =Logger.getLogger(ExportPuntoPrelievoTimeseriesBean.class);
	 LogParam logParam=null;
	 
	 @EJB
	 private IEntityBeanLocal entityUtil;
	 @EJB
	 private UserFacadeLocal userFacade;
	 @EJB
	 private EntityFacadeLocal entityFacade;
	 @EJB
	 private TimeSeriesFacadeLocal timeSeriesFacade;
	 @EJB
	 private FormulaEditorFacadeLocal formulaEditorFacade;
	 @EJB
	 private LoggingFacadePublicLocal loggingManager;
	
	@Override
	public void exportTsPuntoPrelievo(String idAggregato,Integer startDate, Integer endDate, Long idAzione ){
		Date today= new Date();
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(today);
		calendarStartDate.add(Calendar.DAY_OF_MONTH, startDate);
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(today);
		calendarEndDate.add(Calendar.DAY_OF_MONTH, endDate);
		
		exportTsPuntoPrelievo(idAggregato,calendarStartDate.getTime(),calendarEndDate.getTime(),idAzione);
		
	}
	 
	@Override
	public void exportTsPuntoPrelievo(String idAggregato,Date startDate, Date endDate, Long idAzione ){
		UserTO user = new UserTO();
	    try {
	    	user = userFacade.getUserByUsername("admin");
	    } catch (E4CEntityException e2) {
	    	log.error("User not exist"+e2.getMessage());
	    }
	   
	    try {
			logParam = new LogParam("Export timeseries", user.getId(), idAzione == null ? entityFacade.fetchExecutionIdSequence() : idAzione);
		} catch (E4CEntityException e1) {
			log.error("Log param: "+e1);
		}
	    saveInfoLog(logParam, "Start export "+idAggregato);
	    
	    String dotNotation="A_PuntoDiPrelievo[ID='"+idAggregato+"'].PuntoDiPrelievo;";
	    log.info(dotNotation);
		List<EntityTo> puntidiPrelievo=new ArrayList<EntityTo>();
	    try {
			puntidiPrelievo = searchWithDotNotationLight(user,dotNotation);
		} catch (FormulaEditorException e) {
			saveErrorLog(logParam, "Dot notation: "+dotNotation);
			log.error("Execute dot notation "+dotNotation);
			log.error(e);
		}
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    saveInfoLog(logParam, "Trovati "+puntidiPrelievo.size()+" punti di prelievo");
	    XSSFSheet sheet = workbook.createSheet("Export ts");
		XSSFRow row = sheet.createRow(0);
        createHeader(row);
    	CellStyle dateCellStyle = workbook.createCellStyle();
	    
	    for(EntityTo atm:puntidiPrelievo){
	    	GregorianCalendar dateStartGreg=new GregorianCalendar();
	        dateStartGreg.setTime(startDate);
	        GregorianCalendar dateEndGreg=new GregorianCalendar();
	        dateEndGreg.setTime(endDate);
	        dateEndGreg.add(GregorianCalendar.DAY_OF_MONTH, 1);
	    	
	    	EntityFieldValueTO prelevato=atm.getEntityFieldValue("IMP_TOT_PRELEVATO_Mix");
	    	EntityFieldValueTO versamento=atm.getEntityFieldValue("IMP_TOT_VERSAMENTO_Mix");
	    	EntityFieldValueTO prelevatoRiprop=atm.getEntityFieldValue("IMP_TOT_PREL_RIPROP_Mix");
	    	TimeSeriesTO prelevatoTimeSerie=timeSeriesFacade.getTimeSeries(prelevato);
            TimeSeriesTO versamentoTimeSerie=timeSeriesFacade.getTimeSeries(versamento);
            TimeSeriesTO prelevatoRipropTimeSerie=timeSeriesFacade.getTimeSeries(prelevatoRiprop);
            String idATM = atm.getIdCharacter();
            
            dateCellStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat(TimeUtil.getDateFormatForExcel(GranularityType.DAILY)));
            for(GregorianCalendar data=dateStartGreg;data.before(dateEndGreg);data.add(GregorianCalendar.DAY_OF_MONTH, 1)){
        		row=sheet.createRow(sheet.getLastRowNum()+1);
        	    
        	    row.createCell(0).setCellValue(idATM);
        		XSSFCell cellDate = row.createCell(1);
                cellDate.setCellValue(data.getTime());
                cellDate.setCellStyle(dateCellStyle);
                
                
                setValueCell(prelevatoTimeSerie,data,row,2);
                setValueCell(versamentoTimeSerie,data,row,3);
                setValueCell(prelevatoRipropTimeSerie,data,row,4);
            }
            
	    	
	    }
        saveFileinFolder(workbook);
        saveInfoLog(logParam, "End export");
	}

	private void saveFileinFolder(XSSFWorkbook workbook) {
		String pathFile = PropertiesUtil.getPropertyOrDefault("export.puntiDiPrelievo.timeseries", null);
		try {
			FileUtil.createPathIfNotExists(pathFile);
		} catch (IOException e) {
			e.printStackTrace();
			saveErrorLog(logParam, "Create path: "+pathFile);
		}
		Date today=new Date();
		DateFormat df = new SimpleDateFormat("ddMMyyyy_HHmm");
		String nameFile="PuntiDiprelievo"+df.format(today)+".xlsx";
		FileOutputStream fos;
		try {
			ByteArrayOutputStream dataOutputStream = new ByteArrayOutputStream();
			workbook.write(dataOutputStream);
			
			File fileExcel = new File(pathFile+nameFile);
			fos = new FileOutputStream (fileExcel);
			dataOutputStream.writeTo(fos);
			fos.close();
			saveInfoLog(logParam, "Save file in path: "+pathFile);
		} catch (IOException e1) {
			e1.printStackTrace();
			saveErrorLog(logParam, "Save file in path: "+pathFile);
		} 


	}

	
	private void setValueCell(TimeSeriesTO timeseries, GregorianCalendar data, XSSFRow row, int i) {
        if(timeseries!=null){
            int first=timeseries.getActualStartDate().compareTo(data.getTime());
            int last=timeseries.getActualEndDate().compareTo(data.getTime());
            try {
                if((first<=0)&&(last>=0)){
                    BigDecimal value=timeseries.getValue(data.getTime());
                    if(value!=null){
                    	row.createCell(i).setCellValue(value.floatValue());
                    }
                }
            } catch (MissingDateException e) {
                log.error("Error: date not found in timeseries "+e.getMessage());
            }
        }
		
	}

	private void createHeader(XSSFRow row) {
		XSSFCell cellID = row.createCell(0);
		cellID.setCellValue("ID");
		XSSFCell cellRemi = row.createCell(1);
	    cellRemi.setCellValue("Date");
	    XSSFCell cellTipoEnerg = row.createCell(2);
	    cellTipoEnerg.setCellValue("Erogato ForeMix");
	    XSSFCell cellBestQuantityVolume = row.createCell(3);
	    cellBestQuantityVolume.setCellValue("Versato ForeMix");
	    XSSFCell cellCapacitaShipper = row.createCell(4);
	    cellCapacitaShipper.setCellValue("Erogato Riporpozione A Monte ForeMix");
	}

	public List<EntityTo> searchWithDotNotationLight( UserTO userTo, String searchString)
            throws FormulaEditorException {
        List<EntityTo> list = new ArrayList<>();
        FormulaResultDTO result = formulaEditorFacade.evalFormula((FEInstance) null, searchString, userTo);
        FEDataType instance = result.getReturnValue();

        if (instance instanceof FEInstance) {
            EntityTo entity = entityUtil.getEntity(((FEInstance) instance).getObject(), LevelLoadDTO.LIGHT, userTo);
            if(entity != null)
                list.add(entity);
        } else
            if (instance instanceof FEArray) {
                for (FEDataType inst : ((FEArray) instance).getList()) {
                    EntityTo entity = entityUtil.getEntity(((FEInstance) inst).getObject(), LevelLoadDTO.LIGHT, userTo);
                    if(entity != null)
                        list.add(entity);

                }
            }

        return list;
    }

	private void saveInfoLog(LogParam logParam, String msg) {
		if(logParam!=null)
			LoggingUtils.info(logParam, loggingManager, msg);
	}
	    
	private void saveErrorLog(LogParam logParam, String msg) {
		if(logParam!=null)
			LoggingUtils.error(logParam, loggingManager, msg);
	}
}
