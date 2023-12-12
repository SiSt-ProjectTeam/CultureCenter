package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.MemberDTO;

public interface PaymentService {

	MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException;

}
