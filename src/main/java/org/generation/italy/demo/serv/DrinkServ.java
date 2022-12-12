package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.repo.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkServ {
	@Autowired
	private DrinkRepo drinkRepo;
	
	public void save(Drink drink) {
		drinkRepo.save(drink);
	}
	
	public List<Drink> all(){
		return drinkRepo.findAll();
	}
	
	public Optional<Drink> findDrinkById(int id){
		return drinkRepo.findById(id);
	}
	
	public void deleteById(int id) {
		drinkRepo.deleteById(id);
	}
	
	public List<Drink> findByName(String name){
		return drinkRepo.findByNameContainingIgnoreCase(name);
	}
}
