package com.ityueqiangu.project.system.mapper;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

/**
 * @author FlowerStone
 * @title: SysDictDataMapper
 * @description:
 * @date 2022-03-21 16:22:42
 */
public interface SysDictDataMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<SysDictData> selectSysDictDataList(SysDictData sysDictData);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    SysDictData selectSysDictDataById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param sysDictData 新增的记录
     * @return 返回影响行数
     */
    Integer insertSysDictData(SysDictData sysDictData);


    /**
     * 修改
     *
     * @param sysDictData 修改的记录
     * @return 返回影响行数
     */
    Integer updateSysDictData(SysDictData sysDictData);


    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    Integer deleteSysDictDataById(Integer id);

    /**
     * 根据条件删除记录
     *
     * @param sysDictData}
     * @return 返回影响行数
     */
    Integer deleteSysDictDataByCondition(SysDictData sysDictData);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType
     * @return
     * @date 2022年03月22日 0022 10:11:51
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);
}