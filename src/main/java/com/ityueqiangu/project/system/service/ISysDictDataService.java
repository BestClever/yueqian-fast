package com.ityueqiangu.project.system.service;

import java.util.List;
import com.ityueqiangu.project.system.domain.SysDictData;

/**
 * @author FlowerStone
 * @title: SysDictDataService
 * @description:
 * @date 2022-03-21 16:22:42
 */
public interface ISysDictDataService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysDictData> selectSysDictDataList(SysDictData sysDictData);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysDictData
     */
    SysDictData selectSysDictDataById(Integer id);
	
    /**
     * 新增
     *
     * @param sysDictData 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysDictData(SysDictData sysDictData);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysDictData 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysDictData(SysDictData sysDictData);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysDictDataById(Integer id);

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 
     * @return        
     * @date 2022年03月22日 0022 10:07:29
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType
     * @param dictValue
     * @return
     * @date 2022年03月22日 0022 10:13:54
     */
    String selectDictLabel(String dictType, String dictValue);
}