package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.serv.IngredientServ;
import org.generation.italy.demo.serv.PizzaServ;
import org.generation.italy.demo.serv.PromotionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzeriaController {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private IngredientServ ingredientServ;
	
	@Autowired
	private PromotionServ promServ;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizzas = pizzaServ.all();
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("size", pizzas.size());
		return "Main";
	}
	
	@GetMapping("create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promotion> promotions = promServ.all();
		model.addAttribute("promotions", promotions);
		
		List<Ingredient> ingredients = ingredientServ.all();
		model.addAttribute("ingredients", ingredients);
		
		return "Create";
	}
	
	@PostMapping("store")
	public String store(@Valid Pizza pizza) {
		
		pizzaServ.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("update/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		Optional<Pizza> pizza = pizzaServ.findPizzaById(id);
		model.addAttribute("pizza", pizza);
		
		List<Promotion> promotions = promServ.all();
		model.addAttribute("promotions", promotions);
		
		List<Ingredient> ingredients = ingredientServ.all();
		model.addAttribute("ingredients", ingredients);
		
		return "Update";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<Pizza> pizza = pizzaServ.findPizzaById(id);
		pizzaServ.deleteById(id);
		
		return "redirect:/";
	}
	
	@GetMapping("search")
	public String getSearchPizzaByName(Model model, 
			@RequestParam(name = "query", required = false) String query) {
		
//		List<Pizza> pizzas = null;
//		if (query == null) {
//			
//			pizzas = pizzaServ.all();
//			
//		} else {
//			
//			pizzas = pizzaServ.findByName(query);
//		}
		List<Pizza> pizzas = query == null 
							? pizzaServ.all()
							: pizzaServ.findByName(query); 
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("size", pizzas.size());
		model.addAttribute("query", query);
		
		return "Main";
	}

}
