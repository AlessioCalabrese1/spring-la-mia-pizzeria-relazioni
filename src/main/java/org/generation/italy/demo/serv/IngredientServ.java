package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IngredientServ {
	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> all(){
		return ingredientRepo.findAll();
	}
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void deleteById(int id) {
		ingredientRepo.deleteById(id);
	}
	
	public Optional<Ingredient> findById(int id) {
		return ingredientRepo.findById(id);
	}
}
