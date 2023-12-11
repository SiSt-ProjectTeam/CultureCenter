package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmSearchDTO {
	    
	    private String pageIndex;
	    private String type;
	    private String lectSmsterCd;
	    private String yy;
	    private String q;
	    private String atlctRsvNo;
	    private String initIndex;
	    private String listCnt;
	    
	    private String prevSesterYy;
	    private String prevSesterLectSmsterCd;
	    
	    private String orderSet;
	    private String brchCd; 

} // class
