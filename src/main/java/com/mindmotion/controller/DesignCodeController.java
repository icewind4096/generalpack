package com.mindmotion.controller;

import com.mindmotion.domain.Designcode;
import com.mindmotion.service.DesigncodeService;
import com.mindmotion.utils.ResultVOUtil;
import com.mindmotion.vo.DesigncodeVO;
import com.mindmotion.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * DesignCode
 * Created by mecwa on 2019/9/23.
 */

@RestController
@RequestMapping("/designcode")
public class DesignCodeController {
    @Autowired
    DesigncodeService designcodeService;

    @GetMapping("/list")
    public ResultVO List(){
        //1. 查询出全部的designcode
        Page<Designcode> designcodeList = designcodeService.findAll();

        //2. 拼装数据
        List<DesigncodeVO> designcodeVOList = new ArrayList<>();
        for (Designcode designcode: designcodeList){
            DesigncodeVO designcodeVO = new DesigncodeVO();
            BeanUtils.copyProperties(designcode, designcodeVO);
            designcodeVOList.add(designcodeVO);
        }

        return ResultVOUtil.success(designcodeList);
    }
}
