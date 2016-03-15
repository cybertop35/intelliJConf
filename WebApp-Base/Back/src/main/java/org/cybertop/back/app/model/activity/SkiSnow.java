package org.cybertop.back.app.model.activity;



import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.cybertop.back.app.model.EditableImpl;
import org.cybertop.back.app.model.IntegerIdentifiable;

@Entity(name="T_SKY_SNOW_ACTIVITY")
public class SkiSnow extends EditableImpl implements IntegerIdentifiable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String place;	
	@Column
	private int time;
	@Column
	private int priceSkypass;
	@Column
	private Date date;
	@Column
	private String note;
	
	@OneToMany()
	private List<BusStop> busStop;
	
	public SkiSnow() {
		super();

	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPriceSkypass() {
		return priceSkypass;
	}

	public void setPriceSkypass(int priceSkypass) {
		this.priceSkypass = priceSkypass;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + priceSkypass;
		result = prime * result + time;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkiSnow other = (SkiSnow) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (priceSkypass != other.priceSkypass)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	

	
	
}
