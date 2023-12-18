package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.WaitingListDTO;

public interface MypageWaitingMapper {
	
	public List<WaitingListDTO> getWaitingList(int member_sq) throws ClassNotFoundException, SQLException;

	public List<WaitingListDTO> getWaitingList(@Param("member_sq") int member_sq, @Param("dto") FrmSearchDTO params) throws ClassNotFoundException, SQLException;

	public int deleteChildren(int late_sq) throws ClassNotFoundException, SQLException;
	
	public int deleteClass(int late_sq) throws ClassNotFoundException, SQLException;
	
	public int totCnt(@Param("member_sq") int member_sq, @Param("dto") FrmSearchDTO params)throws ClassNotFoundException, SQLException;;
	
}
