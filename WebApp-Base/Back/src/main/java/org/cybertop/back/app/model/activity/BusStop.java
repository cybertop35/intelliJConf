package org.cybertop.back.app.model.activity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.cybertop.back.app.model.EditableImpl;
import org.cybertop.back.app.model.IntegerIdentifiable;

@Entity(name="T_BUS_STOP")
public class BusStop extends EditableImpl implements IntegerIdentifiable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String place;
	@Column
	private Calendar time;
	@Column
	private int timeGroup;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id= id;
		
	}

	
	
}
