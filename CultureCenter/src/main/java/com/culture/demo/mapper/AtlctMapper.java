package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.AtlctPersonalDTO;

public interface AtlctMapper {
	
	public Map<AtlctDTO, ArrayList<AtlctPersonalDTO>> selectAtlctList(int orderSq, boolean isCancelled, String year, int smstId, String searchText, int memberSq) throws SQLException;
	
}
