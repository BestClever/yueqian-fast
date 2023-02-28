package com.ityueqiangu.project.system.service;

import java.util.List;
import com.ityueqiangu.project.system.domain.SysNotice;

/**
 * @author FlowerStone
 * @title: SysNoticeService
 * @description:
 * @date 2022-03-18 22:09:31
 */
public interface ISysNoticeService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysNotice> selectSysNoticeList(SysNotice sysNotice);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysNotice
     */
    SysNotice selectSysNoticeById(Integer id);
	
    /**
     * 新增
     *
     * @param sysNotice 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysNotice(SysNotice sysNotice);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysNotice 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysNotice(SysNotice sysNotice);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysNoticeById(Integer id);
	
}