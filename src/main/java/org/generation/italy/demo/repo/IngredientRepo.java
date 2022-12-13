package org.generation.italy.demo.repo;

import org.generation.italy.demo.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer>{

}
