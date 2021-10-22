package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.FineDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.FineMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Fine;
import com.switchfully.ctrlaltdefeatdigibooky.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineService {
	private final FineRepository fineRepository;

	public FineService(FineRepository fineRepository) {this.fineRepository =
			  fineRepository;
	}

	//add fine
	public void addFine(FineDto fineDto){
		fineRepository.addFine(FineMapper.getFine(fineDto));
	}
	//get all fines
	public List<FineDto> getFines(){
		return fineRepository.getRepository()
				  .stream()
				  .map(FineMapper :: getDto)
				  .toList();
	}
	//get fines by user
	public List<FineDto> getFinesFromUser(String id){
		return fineRepository.getRepository().stream()
				  .filter(fine -> fine.userId().equals(id))
				  .map(FineMapper::getDto)
				  .toList();
	}
	//Calculate Fine

}
