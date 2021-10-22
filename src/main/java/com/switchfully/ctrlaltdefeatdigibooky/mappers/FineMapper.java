package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.dto.FineDto;
import com.switchfully.ctrlaltdefeatdigibooky.model.Fine;
import com.switchfully.ctrlaltdefeatdigibooky.model.FineType;

public class FineMapper {

	public static Fine getFine(FineDto dto){
		return new Fine(dto.fineType(), dto.userId(),
				  Double.parseDouble(dto.amount()));
	}
	public static FineDto getDto(Fine fine){
		return new FineDto(fine.type(), fine.userId(),
				  String.valueOf(fine.amount()));
	}
}
