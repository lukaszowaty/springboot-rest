package pl.project.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.project.model.Car;
import pl.project.repository.CarRepository;
import pl.project.view.ExcelView;
import pl.project.view.MyPdfView;

@Controller
@RequestMapping("/car")
public class CarController {

	private CarRepository carRepository;

	@Autowired
	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@GetMapping
	public String listCars(Model model) {
		List<Car> cars = carRepository.findAll();
		model.addAttribute("cars", cars);
		return "carList";
	}
	
	
	@PostMapping
	public String addCar(@ModelAttribute Car car, RedirectAttributes redirectAttributes) {
		carRepository.save(car);
		redirectAttributes.addFlashAttribute("message", "Car added successfuly");
		return "redirect:/";
	}
	
	@GetMapping("/report")
	public ModelAndView report() {
		
		Map<String, Object> model = new HashMap<>();
		
		List<Car>  cars = carRepository.findAll();
		Collections.sort(cars);
		model.put("cars", cars);
		
		return new ModelAndView(new MyPdfView(), model);
	}
	
	@GetMapping("/download")
	public ModelAndView reportXLS() {
		
		Map<String, Object> model = new HashMap<>();
		
		List<Car>  cars = carRepository.findAll();
		Collections.sort(cars);
		model.put("cars", cars);
		
		return new ModelAndView(new ExcelView(), model);
	}
}
