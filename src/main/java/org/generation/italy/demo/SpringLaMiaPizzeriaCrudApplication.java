package org.generation.italy.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.serv.DrinkServ;
import org.generation.italy.demo.serv.IngredientServ;
import org.generation.italy.demo.serv.PizzaServ;
import org.generation.italy.demo.serv.PromotionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private DrinkServ drinkServ;
	
	@Autowired
	private PromotionServ promoServ;
	
	@Autowired
	private IngredientServ ingredientServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY);
		
		LocalDate startDateProm1 = LocalDate.of(2023, 6, 13);
		LocalDate endDateProm1 = LocalDate.of(2023, 8, 23);
		Promotion pro1 = new Promotion("Promo1", startDateProm1, endDateProm1);
		
		LocalDate startDateProm2 = LocalDate.of(2022, 2, 15);
		LocalDate endDateProm2 = LocalDate.of(2024, 8, 23);
		Promotion pro2 = new Promotion("Promo2", startDateProm2, endDateProm2);
		
		LocalDate startDateProm3 = LocalDate.of(2024, 6, 13);
		LocalDate endDateProm3 = LocalDate.of(2026, 8, 23);
		Promotion pro3 = new Promotion("Promo3", startDateProm3, endDateProm3);
		
		promoServ.save(pro1);
		promoServ.save(pro2);
		promoServ.save(pro3);
		
		System.out.println("-----------------------------------------------------------");
		
		Ingredient i1 = new Ingredient("Ingredient 1");
		Ingredient i2 = new Ingredient("Ingredient 2");
		
		ingredientServ.save(i1);
		ingredientServ.save(i2);
		
		List<Ingredient> ingredients = ingredientServ.all();
		System.err.println(ingredients);
		
		System.out.println("-----------------------------------------------------------");
		
		Pizza p1 = new Pizza("Margherita", "é una margherita", 5, pro1, i1);
		Pizza p2 = new Pizza("Rustica", "patatine e salsiccia", 7, pro1);
		Pizza p3 = new Pizza("Siciliana", "margherita con melanzane", 8, pro3, i1, i2);
		
		pizzaServ.save(p1);
		pizzaServ.save(p2);
		pizzaServ.save(p3);
		
		List<Pizza> pizzas = pizzaServ.all();
		//System.err.println(pizzas);
		
		System.out.println("-----------------------------------------------------------");
		
		List<Promotion> promotions = promoServ.allWPizzas();
		for (Promotion promotion : promotions) {
			System.err.println(promotion.getPizzas());
		}
		
		//promoServ.deleteById(1);
		
		promotions = promoServ.allWPizzas();
		for (Promotion promotion : promotions) {
			System.err.println(promotion.getPizzas());
		}
		System.out.println();
		pizzas = pizzaServ.all();
		for (Pizza pizza : pizzas) {
			System.err.println(pizza);
		}
		System.out.println("-----------------------------------------------------------");
		
		Drink d1 = new Drink("LosPollos", "eso un pollos", 10000);
		Drink d2 = new Drink("ChicaMala", "eso una chica malissima", 1);
		Drink d3 = new Drink("Mojito", null , 5);
		
		drinkServ.save(d1);
		drinkServ.save(d2);
		drinkServ.save(d3);
		
		List<Drink> drinks = drinkServ.all();
		//System.err.println(drinks);
	}
}
















