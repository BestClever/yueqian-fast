package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysNotice;

/**
 * @author Clever、xia
 * @title: SysNoticeMapper
 * @description:
 * @date 2021-05-08 16:38:48
 */
@Mapper
public interface SysNoticeMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysNotice> selectSysNoticeList(SysNotice sysNotice);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysNotice selectSysNoticeById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysNotice 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysNotice(SysNotice sysNotice);
	
	
	/**
     * 修改
     *
     * @param sysNotice 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysNotice(SysNotice sysNotice);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysNoticeById(Integer id);
	
}