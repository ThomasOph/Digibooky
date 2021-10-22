package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Fine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FineRepository {
	List<Fine> fines = new ArrayList<>();

	public List<Fine> getRepository() {
		return fines;
	}

	public void addFine(Fine fine){
		fines.add(fine);
	}

	public List<Fine> getFinesByUserId(String userId){
		return fines
				  .stream()
				  .filter(fine -> fine.userId().equals(userId))
				  .toList();
	}

}
