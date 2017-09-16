package com.accenture.export.timeseries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.i4c.antares.common.to.TimeSeriesTO;
import com.i4c.antares.dotnotation.datatypes.FEDataType;
import com.i4c.antares.dotnotation.datatypes.FEString;

public class ExportPuntoPrelievoDTO {

	String labelColumn;
	String nameAttribute;
	int numColumn;
	TimeSeriesTO timeseries;
	private static Map<String, String> attributeLabel;
	static {
        Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("IMP_TOT_PRELEVATO_Mix","Erogato ForeMix");
		aMap.put("IMP_TOT_VERSAMENTO_Mix","Versato ForeMix");
		aMap.put("IMP_TOT_PREL_RIPROP_Mix","Erogato Riporpozione A Monte ForeMix");
		aMap.put("NUM_MINUTI_PUNTO_ATTIVO","Minuti di Servizio");
		aMap.put("NUM_MINUTI_BANCONOTE","Disponibilita Banconote");
		aMap.put("PRC_AVAILABILITY","Availability");
		aMap.put("PRC_LIVELLO_SERVIZIO","Livello di Servizio");
		aMap.put("IMP_TOT_PRELEVATO","Erogato");
		aMap.put("NUM_PRELIEVI","Prelievi");
		aMap.put("IMP_EMG","Erogato Medio Giornaliero");
		aMap.put("IMP_TOT_VERSAMENTO","Versato");
		aMap.put("NUM_VERSAMENTI","Versamenti");
		aMap.put("NUM_COEFFICIENTE_GIORNO","Coefficienti");
		aMap.put("IMP_SOGLIA","Soglia Calcolata Erogato medio");
		aMap.put("FLG_RICARICABILE","Giorni di ricarica");
		aMap.put("NUM_GG_RICARICA_2","Giorni da coprire");
		aMap.put("IMP_CARICO_PRELIEVO","Importo del Caricato Cassetto Prelievi");
		aMap.put("IMP_RITIRATO_PRELIEVO","Importo del Ritirato Cassetto Prelievi");
		aMap.put("IMP_RITIRATO_DEPOSITO","Importo del Ritirato Cassetto Depositi");
		aMap.put("IMP_SOGLIA_PREVISIONE","Soglia Calcolata Sulla Previsione");
		aMap.put("NUM_LIVELLO_STAGIONE","Livello della stagione");
		aMap.put("IMP_CARICO_STAGIONE","Importo da caricare previsto per la stagione in cui ci si trova");
		aMap.put("IMP_TOT_PRELEVATO_Auto","Erogato ForeAuto");
		aMap.put("IMP_TOT_VERSAMENTO_Auto","Versato ForeAuto");
		aMap.put("PrelevatoLagSerie_2","PrelevatoLagSerie_2");
		aMap.put("PrelevatoLagSerie_3","PrelevatoLagSerie_3");
		aMap.put("PrelevatoLagSerie_4","PrelevatoLagSerie_4");
		aMap.put("PrelevatoLagSerie_5","PrelevatoLagSerie_5");
		aMap.put("PrelevatoLagSerie_6","PrelevatoLagSerie_6");
		aMap.put("VersamentoLagSerie_2","VersamentoLagSerie_2");
		aMap.put("VersamentoLagSerie_3","VersamentoLagSerie_3");
		aMap.put("VersamentoLagSerie_4","VersamentoLagSerie_4");
		aMap.put("VersamentoLagSerie_5","VersamentoLagSerie_5");
		aMap.put("VersamentoLagSerie_6","VersamentoLagSerie_6");
		aMap.put("IMP_TOT_PREL_RIPROP_Auto","Erogato Riporpozione A Monte ForeAuto");
		aMap.put("NUM_FUORILISTA","Fuori Lista");
		aMap.put("QuantilePrelevato1","Quantile Prelevato 1");
		aMap.put("QuantilePrelevato2","Quantile Prelevato 2");
		aMap.put("QuantilePrelevato3","Quantile Prelevato 5");
		aMap.put("QuantilePrelevato95","Quantile Prelevato 95");
		aMap.put("QuantilePrelevato98","Quantile Prelevato 98");
		aMap.put("QuantilePrelevato99","Quantile Prelevato 99");
		aMap.put("QuantileVersamento1","Quantile Versamento 1");
		aMap.put("QuantileVersamento2","Quantile Versamento 2");
		aMap.put("QuantileVersamento5","Quantile Versamento 5");
		aMap.put("QuantileVersamento95","Quantile Versamento 95");
		aMap.put("QuantileVersamento98","Quantile Versamento 98");
		aMap.put("QuantileVersamento99","Quantile Versamento 99");
        attributeLabel = Collections.unmodifiableMap(aMap);
    }
	
	
	
	public ExportPuntoPrelievoDTO(String nameAttribute, String labelColumn,int numColumn) {
		super();
		this.labelColumn = labelColumn;
		this.nameAttribute = nameAttribute;
		this.numColumn = numColumn;
	}
	
/*	public static List<ExportPuntoPrelievoDTO> listAttributeToExport(){
		List<ExportPuntoPrelievoDTO> result = new ArrayList<ExportPuntoPrelievoDTO>();
		int i=2;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PRELEVATO_Mix","Erogato ForeMix",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_VERSAMENTO_Mix","Versato ForeMix",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PREL_RIPROP_Mix","Erogato Riporpozione A Monte ForeMix",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_MINUTI_PUNTO_ATTIVO","Minuti di Servizio",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_MINUTI_BANCONOTE","Disponibilita Banconote",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PRC_AVAILABILITY","Availability",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PRC_LIVELLO_SERVIZIO","Livello di Servizio",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PRELEVATO","Erogato",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_PRELIEVI","Prelievi",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_EMG","Erogato Medio Giornaliero",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_VERSAMENTO","Versato",i));i++;
		
		result.add(new ExportPuntoPrelievoDTO("NUM_VERSAMENTI","Versamenti",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_COEFFICIENTE_GIORNO","Coefficienti",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_SOGLIA","Soglia Calcolata Erogato medio",i));i++;
		result.add(new ExportPuntoPrelievoDTO("FLG_RICARICABILE","Giorni di ricarica",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_GG_RICARICA_2","Giorni da coprire",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_CARICO_PRELIEVO","Importo del Caricato Cassetto Prelievi",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_RITIRATO_PRELIEVO","Importo del Ritirato Cassetto Prelievi",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_RITIRATO_DEPOSITO","Importo del Ritirato Cassetto Depositi",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_SOGLIA_PREVISIONE","Soglia Calcolata Sulla Previsione",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_LIVELLO_STAGIONE","Livello della stagione",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_CARICO_STAGIONE","Importo da caricare previsto per la stagione in cui ci si trova",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PRELEVATO_Auto","Erogato ForeAuto",i));i++;
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_VERSAMENTO_Auto","Versato ForeAuto",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PrelevatoLagSerie_2","PrelevatoLagSerie_2",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PrelevatoLagSerie_3","PrelevatoLagSerie_3",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PrelevatoLagSerie_4","PrelevatoLagSerie_4",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PrelevatoLagSerie_5","PrelevatoLagSerie_5",i));i++;
		result.add(new ExportPuntoPrelievoDTO("PrelevatoLagSerie_6","PrelevatoLagSerie_6",i));i++;
		result.add(new ExportPuntoPrelievoDTO("VersamentoLagSerie_2","VersamentoLagSerie_2",i));i++;
		result.add(new ExportPuntoPrelievoDTO("VersamentoLagSerie_3","VersamentoLagSerie_3",i));i++;
		result.add(new ExportPuntoPrelievoDTO("VersamentoLagSerie_4","VersamentoLagSerie_4",i));i++;
		result.add(new ExportPuntoPrelievoDTO("VersamentoLagSerie_5","VersamentoLagSerie_5",i));i++;
		result.add(new ExportPuntoPrelievoDTO("VersamentoLagSerie_6","VersamentoLagSerie_6",i));i++;
		
		result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PREL_RIPROP_Auto","Erogato Riporpozione A Monte ForeAuto",i));i++;
		result.add(new ExportPuntoPrelievoDTO("NUM_FUORILISTA","Fuori Lista",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato1","Quantile Prelevato 1",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato2","Quantile Prelevato 2",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato3","Quantile Prelevato 5",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato95","Quantile Prelevato 95",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato98","Quantile Prelevato 98",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantilePrelevato99","Quantile Prelevato 99",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento1","Quantile Versamento 1",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento2","Quantile Versamento 2",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento5","Quantile Versamento 5",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento95","Quantile Versamento 95",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento98","Quantile Versamento 98",i));i++;
		result.add(new ExportPuntoPrelievoDTO("QuantileVersamento99","Quantile Versamento 99",i));
		return result; 
	}*/
	
	

	public static List<ExportPuntoPrelievoDTO> listAttributeToExport(List<FEDataType> attributeToExportForumla) {
		List<ExportPuntoPrelievoDTO> result = new ArrayList<ExportPuntoPrelievoDTO>();
		int i=2;
		for(FEDataType attr:attributeToExportForumla){
			FEString keyAttribut=(FEString)attr;
			if(attributeLabel.containsKey(keyAttribut.toString())){
				result.add(new ExportPuntoPrelievoDTO(keyAttribut.toString(),attributeLabel.get(keyAttribut.toString()),i));i++;
			}
			
		}
		
		
		
/*		if(attributeToExportForumla.contains(new FEString("IMP_TOT_PRELEVATO_Mix")))
			result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PRELEVATO_Mix","Erogato ForeMix",i));i++;
		if(attributeToExportForumla.contains(new FEString("IMP_TOT_VERSAMENTO_Mix")))
			result.add(new ExportPuntoPrelievoDTO("IMP_TOT_VERSAMENTO_Mix","Versato ForeMix",i));i++;
		if(attributeToExportForumla.contains(new FEString("IMP_TOT_PREL_RIPROP_Mix")))
			result.add(new ExportPuntoPrelievoDTO("IMP_TOT_PREL_RIPROP_Mix","Erogato Riporpozione A Monte ForeMix",i));i++;*/
		return result; 
	}
	

	public String getLabelColumn() {
		return labelColumn;
	}

	public String getNameAttribute() {
		return nameAttribute;
	}

	public int getNumColumn() {
		return numColumn;
	}

	public TimeSeriesTO getTimeseries() {
		return timeseries;
	}

	public void setTimeseries(TimeSeriesTO timeseries) {
		this.timeseries = timeseries;
	}
	
	
	
}
