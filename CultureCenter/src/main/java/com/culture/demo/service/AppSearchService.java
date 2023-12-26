package com.culture.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.ClassFormDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.domain.ReviewDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.domain.TawardsDTO;
import com.culture.demo.domain.TcareerDTO;
import com.culture.demo.domain.TcertificateDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.domain.TeducationDTO;

public interface AppSearchService {

	// 강좌 정보 가져오기 - 지점으로 찾기
	List<ClassDTO> selectClassList(@Param("branch_id") int branch_id, 
									@Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
								    @Param("yyl") String[] yyl,
								    @Param("lectcll") String[] lectcll,
								    @Param("lectstl") String[] lectstl,
								    @Param("dayl") String[] dayl,
								    @Param("timel") String[] timel,
								    @Param("amtl") String[] amtl) throws Exception;
	
	// 강좌 목록 ajax html 생성 - 지점으로 찾기
	String lecHTML(@Param("branch_id") int branch_id, 
					@Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
				    @Param("yyl") String[] yyl,
				    @Param("lectcll") String[] lectcll,
				    @Param("lectstl") String[] lectstl,
				    @Param("dayl") String[] dayl,
				    @Param("timel") String[] timel,
				    @Param("amtl") String[] amtl) throws Exception;

	
	// 메인페이지 강좌 정보 가져오기
	List<ClassDTO> getMainClassList(MainLectSearchDTO mainLectSearchDTO) throws Exception;
	
	// 추천/카테고리별/신규 강좌 목록 ajax html 생성
	String mainLecHTML(MainLectSearchDTO mainLectSearchDTO, HttpServletRequest request) throws Exception;
	

	// 강좌 상세정보 가져오기
	ClassDTO DetailClassInfo(@Param("branch_id") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd") int lectSmsterCd, @Param("lectCd") int lectCd) throws Exception;

	// 강사 프로필 가져오기 - 강사 테이블
	TeacherDTO selectTeacherInfo(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 -  학력
	List<TeducationDTO> eduInfo(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 경력
	List<TcareerDTO> careerInfo(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 수상
	List<TawardsDTO> awardInfo(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강사 프로필 가져오기 - 자격
	List<TcertificateDTO> certiInfo(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강사 프로필 ajax html 생성
	String teacherHTML(@Param("tcCdNo") int member_sq) throws Exception;
	
	// 강좌 상세보기 + 옵션 정보
	ClassDTO selectClassInfo(@Param("branch_id") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd") int lectSmsterCd, @Param("lectCd") int lectCd) throws Exception;

	// 강좌 목록 ajax html 생성 - 강좌로 찾기
	String cateLecHTML(SearchBranchDTO searchCategoryDTO, String[] brchCdl, String[] yyl, String[] lectcll,
			String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception;

	// 강좌 정보 가져오기 - 강좌로 찾기
	List<ClassDTO> selectCateClassList(SearchBranchDTO searchCategoryDTO, String[] brchCdl, String[] yyl,
			String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception;

	// 강좌 검색 강좌 정보 가져오기
	List<ClassDTO> selectInteList(SearchBranchDTO searchCategoryDTO, String[] brchCdl, String[] yyl,
			String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception;
	
	// 강좌 검색 ajax html 생성
	String inteHTML(SearchBranchDTO searchBranchDTO, String[] brchCdl, String[] yyl, String[] lectcll, String[] lectstl,
			String[] dayl, String[] timel, String[] amtl) throws Exception;

	// 강좌 상세 - 리뷰 목록 ajax html 생성
	String reviewListHTML(ClassFormDTO classFormDTO) throws Exception;
	
	// 강좌 상세 - 리뷰 목록 정보 가져오기
	List<ReviewDTO> getReviewList(ClassFormDTO classFormDTO) throws Exception;

	// 강좌 상세 - 리뷰 팝업 정보 가져오기
	ReviewDTO getReviewDtl(int brchCd, int yy, int lectSmsterCd, int lectCd, int tcNo, int mbrNo) throws Exception;
		
	// 강좌 상세 - 리뷰 팝업 ajax html 생성
	String reviewDtlHTML(int brchCd, int yy, int lectSmsterCd, int lectCd, int tcNo, int mbrNo) throws Exception;
	
	// 대기접수 회원 정보 가져오기
	MemberDTO selectMemberInfo(int member_sq);

	// 대기접수 자녀회원 정보 가져오기
	List<ChildrenDTO> selectChildInfo(int member_sq);

}
