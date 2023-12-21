package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFrmDTO {
	// frm_temp
    private String atlctRsvNo;
    private String lpntUseAmt;
    private String strPasswd;
    private String strCardNo;
    private String brchCd;
    private String strCoprMemstrNo;
    private String crdStlmAmt;
    private String goodsName;
    private String merchantID;
    private String moid;				// 상점 주문번호
    private String buyerName;
    private String pgMctKeyVal;
    private String mbrNo;
    private String mbrGrdeCd;
    private String mvgBlstrCd;
    private String mbrId;
}
