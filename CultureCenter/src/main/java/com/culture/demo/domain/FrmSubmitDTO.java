package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmSubmitDTO {
    private String brchCd;
    private String yy;
    private String lectSmsterCd;
    private String lectCd;
    private String optnSeqno;
    private String optnUseYn;
    private String lectDetailSq;
}
