package com.culture.demo.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.FrmSearchDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ComplexServiceImpl implements ComplexService{
	private CartService cartService;
	private MemberService memberService;
	private AtlctService atlctService;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ArrayList<AtlctDTO> paymentStep3(int orderSq, int member_sq) throws Exception{
		log.info(">>>ComplexService.paymentStep3()");
		//장바구니 삭제
		String cartSeqno = this.cartService.getDetailSqByOrderSq(orderSq);
		cartService.delete(member_sq, "pay",cartSeqno);
		//수강내역 증가
		int addCnt = (int) Arrays.asList(cartSeqno.split(",")).stream().count();
		memberService.updateOrderClass(member_sq,addCnt);

		//정보
		FrmSearchDTO dto = new FrmSearchDTO();
		dto.setAtlctRsvNo(orderSq);
		ArrayList<AtlctDTO> list = atlctService.getAtlctList(dto, member_sq);
		System.out.println("step3 response List : " + list);
		AtlctDTO dtoTemp = new AtlctDTO();
		int class_fee = 0;
		int ex_charge = 0;
		int total_amt = list.get(0).getTotal_amt();
		for (AtlctDTO atlctdto : list) {
			class_fee += atlctdto.getClass_fee() * atlctdto.getPersonalList().size();
			ex_charge += atlctdto.getEx_charge() * atlctdto.getPersonalList().size();
		}
		dtoTemp.setClass_fee(class_fee);
		dtoTemp.setEx_charge(ex_charge);
		dtoTemp.setOrder_amt(class_fee+ex_charge);
		dtoTemp.setTotal_amt(total_amt);
		if(class_fee+ex_charge == total_amt && ex_charge == 0 ) dtoTemp.setTot_cnt(-1); // no_cost, no_sale
		else if(class_fee+ex_charge == total_amt) dtoTemp.setTot_cnt(-2); // no_sale
		else if(ex_charge == 0 ) dtoTemp.setTot_cnt(-3); // no_cost
		list.add(0,dtoTemp);
		return list;
	}


}
