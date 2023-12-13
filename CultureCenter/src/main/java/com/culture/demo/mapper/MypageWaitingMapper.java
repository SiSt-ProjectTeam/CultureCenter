package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.culture.demo.domain.WaitingListDTO;

@Repository
public interface MypageWaitingMapper {
	
	public List<WaitingListDTO> getWaitingList(int member_sq) throws ClassNotFoundException, SQLException;

	public List<WaitingListDTO> getWaitingList(@Param("member_sq") int member_sq, @Param("branch_nm") String branch_nm) throws ClassNotFoundException, SQLException;

}
