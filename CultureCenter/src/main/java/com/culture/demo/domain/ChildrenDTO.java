package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenDTO {
    private int children_sq;
    private String children_nm;
    private int member_sq;
    private String child_birth_dt;
    private char gender;
    private String realGender;
}
