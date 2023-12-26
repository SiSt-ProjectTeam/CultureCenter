package com.culture.demo.service;

import java.util.ArrayList;

import com.culture.demo.domain.AtlctDTO;

public interface ComplexService {
	
	public ArrayList<AtlctDTO> paymentStep3(int orderSq, int member_sq) throws Exception;
}
