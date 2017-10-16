package pl.project.controller.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.project.model.Car;
import pl.project.repository.CarRepository;

@RestController
@RequestMapping("/api/cars")
public class CarControllerRest {

	private CarRepository carRepository;

	@Autowired
	public CarControllerRest(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> getCars() {
		List<Car> cars = carRepository.findAll();
		
		Collections.sort(cars);
		return cars;
	}
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car getCity(@PathVariable Long id) {
		return carRepository.findOne(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveCar(@RequestBody Car car) {
		carRepository.save(car);
	}
}
