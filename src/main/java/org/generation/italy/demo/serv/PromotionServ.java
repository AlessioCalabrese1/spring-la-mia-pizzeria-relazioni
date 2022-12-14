package org.generation.italy.demo.serv;

import java.util.List;

import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.repo.PromotionRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromotionServ {
	@Autowired
	private PromotionRepo promotionRepo;
	
	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}
	
	public List<Promotion> all() {
		return promotionRepo.findAll();
	}
	
	@Transactional
	public List<Promotion> allWPizzas(){
		List<Promotion> promotions = promotionRepo.findAll();
		
		for (Promotion promotion : promotions) {
			Hibernate.initialize(promotion.getPizzas());
		}
		
		return promotions;
	}
	
	public void deleteById(int id) {
		promotionRepo.deleteById(id);
	}
}	
