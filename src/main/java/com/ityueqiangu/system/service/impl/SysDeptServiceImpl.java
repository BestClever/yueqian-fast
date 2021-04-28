package com.ityueqiangu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.enums.SysFlagStatusEnum;
import com.ityueqiangu.system.domain.SysDept;
import com.ityueqiangu.system.mapper.SysDeptMapper;
import com.ityueqiangu.system.service.ISysDeptService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Clever、xia
 * @title: SysDeptServiceImpl
 * @description:
 * @date 2021-04-25 17:33:01
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService{

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysDept> selectSysDeptList(SysDept sysDept) {
         List<SysDept> sysDepts = sysDeptMapper.selectSysDeptList(sysDept);
         Map<Integer, SysDept> deptMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getId, dept -> dept));
         sysDepts.stream().forEach(dept -> {
             SysDept parentDept = deptMap.get(dept.getParentId());
             if (ObjectUtil.isNotNull(parentDept)) {
                 dept.setParentName(parentDept.getDeptName());
             }
         });
         return sysDepts;
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysDept selectSysDeptById(Integer id) {
    	return sysDeptMapper.selectSysDeptById(id);
    }
	
    /**
     * 新增
     *
     * @param sysDept 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysDept(SysDept sysDept) {
    	return sysDeptMapper.insertSysDept(sysDept);
    }
	
	
    /**
     * 修改
     *
     * @param sysDept 修改的记录
     * @return 返回
     */
    public Integer updateSysDept(SysDept sysDept) {
    	return sysDeptMapper.updateSysDept(sysDept);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysDeptById(Integer id) {
    	return sysDeptMapper.deleteSysDeptById(id);
    }

    /**
     * 查询部门名称是否存在
     * @param sysDept
     * @return
     */
    @Override
    public SysDept existDeptName(SysDept sysDept) {
        sysDept.setIsAvailable(SysFlagStatusEnum.ZERO.getKey());
        return sysDeptMapper.getOne(sysDept);
    }

    @Override
    public List<SysDept> buildTree() {
        ArrayList<SysDept> resultDepts = new ArrayList<>();
        ArrayList<SysDept> parentDepts = new ArrayList<>();
        List<SysDept> sysDepts = sysDeptMapper.selectSysDeptList(null);
        Map<Integer, SysDept> sysDeptMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getId, sysDept -> sysDept));
        for (SysDept sysDept : sysDepts) {
            if (Constants.ZERO.equals(sysDept.getParentId())) {
                resultDepts.add(sysDept);
            }else{
                SysDept parent = sysDeptMap.get(sysDept.getParentId());
                parent.getChildren().add(sysDept);
            }
        }
        return resultDepts;
    }

}