package com.mindmotion.controller;

import com.mindmotion.converter.DesigncodeForm2DesigncodeDTO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.GeneratePackException;
import com.mindmotion.form.DesigncodeForm;
import com.mindmotion.service.DesigncodeService;
import com.mindmotion.utils.ResultVOUtil;
import com.mindmotion.vo.DesigncodeVO;
import com.mindmotion.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DesignCode
 * Created by mecwa on 2019/9/23.
 */

@RestController
@RequestMapping("/designcode")
@Slf4j
public class DesignCodeController {
    @Autowired
    DesigncodeService designcodeService;

    public ResultVO create(@Valid DesigncodeForm designcodeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建Desigcode]->参数不正确， orderForm={}", designcodeForm);
            throw new GeneratePackException(ResultEnum.PARMATER_ERROR.getCode()
                                           ,bindingResult.getFieldError().getDefaultMessage());
        }

        DesigncodeDTO designcodeDTO = DesigncodeForm2DesigncodeDTO.convert(designcodeForm);
        designcodeService.create(designcodeDTO);

        return ResultVOUtil.success();
    }

    @GetMapping("/list")
    public ResultVO List(){
        //1. 查询出全部的designcode
        Page<DesigncodeDTO> designcodeList = designcodeService.findAll();

        //2. 拼装数据
        List<DesigncodeVO> designcodeVOList = new ArrayList<>();
        for (DesigncodeDTO designcodeDTO: designcodeList){
            DesigncodeVO designcodeVO = new DesigncodeVO();
            BeanUtils.copyProperties(designcodeDTO, designcodeVO);
            designcodeVOList.add(designcodeVO);
        }

        return ResultVOUtil.success(designcodeList);
    }
}
