package com.ityueqiangu.project.notice.service;

import java.util.List;
import com.ityueqiangu.project.notice.domain.SysNotice;

/**
 * @author FlowerStone
 * @title: SysNoticeService
 * @description:
 * @date 2021-11-22 15:27:11
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

    /**
     * 获取单个对象
     * @author FlowerStone
     * @date 2021年11月23日 0023 16:35:16
     * @param sysNoticeParam
     * @return
     */
    SysNotice getOne(SysNotice sysNoticeParam);
}