package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.IngredientServ;
import org.generation.italy.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientServ ingredientServ;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping
	public String index(Model model) {
		
		List<Ingredient> ingredients = ingredientServ.all();
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("size", ingredients.size());
		
		List<Pizza> pizzas = pizzaServ.all();
		model.addAttribute("pizzas", pizzas);
		
		return "Ingredient-Main";
	}
	
	@GetMapping("create")
	public String create(Model model) {
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaServ.all();
		model.addAttribute("pizzas", pizzas);
		
		return "Ingredient-Create";
	}
	
	@PostMapping("store")
	public String store(@Valid Ingredient ingredient) {
		
		List<Pizza> pizzas = ingredient.getPizzas();
		if (pizzas != null) {
			for (Pizza pizza : pizzas) {
				pizza.getIngredients().add(ingredient);
			}
		}
		
		ingredientServ.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("update/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		Optional<Ingredient> ingredient = ingredientServ.findById(id);
		model.addAttribute("ingredient", ingredient);
		
		return "Ingredient-Create";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		ingredientServ.deleteById(id);
		
		return "redirect:/ingredient";
	}
}
