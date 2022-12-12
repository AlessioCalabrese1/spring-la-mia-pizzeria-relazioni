package org.generation.italy.demo.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.generation.italy.demo.interfac.PriceableInt;
import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.DrinkServ;
import org.generation.italy.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/priceable")
public class PriceableController {
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private DrinkServ drinkServ;
	
	@GetMapping
	public String getPriciableElements(Model model) {
		List<Pizza> pizzas = pizzaServ.all();
		List<Drink> drinks = drinkServ.all();
		
		List<PriceableInt> priceableElements = new LinkedList<>();
		
		for (int i = 0; i < pizzas.size(); i++) {
			priceableElements.add(pizzas.get(i));
		}
		
		for (int i = 0; i < drinks.size(); i++) {
			priceableElements.add(drinks.get(i));
		}
		
		Collections.sort(priceableElements, new Comparator<PriceableInt>() {
			@Override
			public int compare(PriceableInt o1, PriceableInt o2) {
				
				return o1.getPrice() - o2.getPrice();
			}
		});
		
		
		System.err.println("---------------------");
		System.err.println(priceableElements);
		System.err.println("---------------------");
		
		model.addAttribute("priceableElements", priceableElements);
		
		return "Priceable";
	}
}
