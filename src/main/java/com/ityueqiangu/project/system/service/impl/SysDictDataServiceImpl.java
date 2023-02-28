package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.mapper.SysDictDataMapper;
import com.ityueqiangu.project.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysDictDataServiceImpl
 * @description:
 * @date 2022-03-21 16:22:42
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService{

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysDictData> selectSysDictDataList(SysDictData sysDictData) {
       return sysDictDataMapper.selectSysDictDataList(sysDictData);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysDictData selectSysDictDataById(Integer id) {
    	return sysDictDataMapper.selectSysDictDataById(id);
    }
	
    /**
     * 新增
     *
     * @param sysDictData 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysDictData(SysDictData sysDictData) {
    	return sysDictDataMapper.insertSysDictData(sysDictData);
    }
	
	
    /**
     * 修改
     *
     * @param sysDictData 修改的记录
     * @return 返回
     */
    public Integer updateSysDictData(SysDictData sysDictData) {
    	return sysDictDataMapper.updateSysDictData(sysDictData);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysDictDataById(Integer id) {
    	return sysDictDataMapper.deleteSysDictDataById(id);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType
     * @return
     * @date 2022年03月22日 0022 10:07:46
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return sysDictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     * @date 2022年03月22日 0022 10:14:00
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return sysDictDataMapper.selectDictLabel(dictType, dictValue);
    }

}