package com.accenture.export.timeseries;

import java.util.Date;



public interface IExportPuntoPrelievoTimeseries {

	public void exportTsPuntoPrelievo(String idAggregato,Integer startDate, Integer endDate, Long idAzione );
	
	public void exportTsPuntoPrelievo(String idAggregato,Date startDate, Date endDate, Long idAzione );
	
}
