package com.culture.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.domain.TawardsDTO;
import com.culture.demo.domain.TcareerDTO;
import com.culture.demo.domain.TcertificateDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.domain.TeducationDTO;

public interface AppSearchMapper {
	
	// 강좌 정보 가져오기
	List<ClassDTO> selectClassList(@Param("branch_id") int branch_id, 
									@Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
								    @Param("yyl") String[] yyl,
								    @Param("lectcll") String[] lectcll,
								    @Param("lectstl") String[] lectstl,
								    @Param("dayl") String[] dayl,
								    @Param("timel") String[] timel,
								    @Param("amtl") String[] amtl) throws Exception;
	
	// 강좌 상세정보 가져오기
	ClassDTO DetailClassInfo(@Param("branch_id") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd") int lectSmsterCd, @Param("lectCd") int lectCd) throws Exception;

	// 강사 프로필 정보 가져오기
	TeacherDTO selectTeacherInfo(int member_sq) throws Exception;

	// 강사 프로필 가져오기 -  학력
	List<TeducationDTO> eduInfo(int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 경력
	List<TcareerDTO> careerInfo(int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 수상
	List<TawardsDTO> awardInfo(int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 자격
	List<TcertificateDTO> certiInfo(int member_sq) throws Exception;
	
	// 메인페이지 강좌 정보 가져오기
	List<ClassDTO> mainSelectClassList(@Param("mainLectSearchDTO") MainLectSearchDTO mainLectSearchDTO) throws Exception;
	
}
