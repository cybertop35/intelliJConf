package org.cybertop.back.app.model;


public interface GenericIdentifiable<T> {
	
	public T getId();
	public void setId(T id);
}
