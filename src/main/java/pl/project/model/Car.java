package pl.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car  implements Serializable, Comparable<Car> {
	private static final long serialVersionUID = 7258571780343830216L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String brand;
	private String model;
	@Column(name = "date_production")
	private String dateProduction;
	@Column(name = "car_mileage")
	private int carMileage;
	
	public Car() {}
	
	public Car(String brand, String model, String dateProduction, int carMileage) {
		this.brand = brand;
		this.model = model;
		this.dateProduction = dateProduction;
		this.carMileage = carMileage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDateProduction() {
		return dateProduction;
	}

	public void setDateProduction(String dateProduction) {
		this.dateProduction = dateProduction;
	}

	public int getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(int carMileage) {
		this.carMileage = carMileage;
	}

	@Override
	public int compareTo(Car o) {
		int comparedBrand = brand.compareTo(o.brand);
		
		if(comparedBrand == 0) {
			return model.compareTo(model);
		} else {
			return comparedBrand;
		}
	}
	

}
