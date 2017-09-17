package com.camara.service.bean;

import java.net.URI;
import java.util.Arrays;

public class Product {

	private String marca;
	private String model;
	private String status;
	private String price;
	private URI link;
	private byte[] img;
	
	public Product(String marca, String model, String status, String price,
			URI link) {
		super();
		this.marca = marca;
		this.model = model;
		this.status = status;
		this.price = price;
		this.link = link;
	}
	
	

	public Product(String marca, String model, String status, String price,
			URI link, byte[] img) {
		super();
		this.marca = marca;
		this.model = model;
		this.status = status;
		this.price = price;
		this.link = link;
		this.img = img;
	}



	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}



	@Override
	public String toString() {
		return "Product [marca=" + marca + ", model=" + model + ", status="
				+ status + ", price=" + price + ", link=" + link + ", img="
				+ Arrays.toString(img) + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Product other = (Product) obj;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	
}
