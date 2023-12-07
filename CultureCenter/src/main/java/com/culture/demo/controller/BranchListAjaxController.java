package com.culture.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.mapper.BranchInfoMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class BranchListAjaxController {

    @Setter(onMethod=@__({@Autowired}))
    private BranchInfoMapper branchInfoMapper;

    @PostMapping(value = "/information/branch/list.ajax")
    public List<BranchDTO> selectBranchList(@RequestBody int branch_tp_id) {
        log.info("> /selectBranchList...POST - Ajax");

        return this.branchInfoMapper.selectBranchList(branch_tp_id);
    }
}