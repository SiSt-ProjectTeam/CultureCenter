package AppSearchService;

import java.util.List;

import com.culture.demo.domain.ClassDTO;

public interface LecSearchService {

	// 지점 유형별 지점 이름 얻어오기
	List<ClassDTO> getBranch();
	
	// 대분류별 중분류 카테고리 얻어오기
	List<ClassDTO> getCategory();
	
}
