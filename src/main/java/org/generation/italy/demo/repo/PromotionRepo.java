package org.generation.italy.demo.repo;

import org.generation.italy.demo.pojo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepo extends JpaRepository<Promotion, Integer>{
	
}
