package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.project.system.domain.SysNotice;
import com.ityueqiangu.project.system.mapper.SysNoticeMapper;
import com.ityueqiangu.project.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysNoticeServiceImpl
 * @description:
 * @date 2022-03-18 22:09:31
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService{

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysNotice> selectSysNoticeList(SysNotice sysNotice) {
       return sysNoticeMapper.selectSysNoticeList(sysNotice);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysNotice selectSysNoticeById(Integer id) {
    	return sysNoticeMapper.selectSysNoticeById(id);
    }
	
    /**
     * 新增
     *
     * @param sysNotice 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysNotice(SysNotice sysNotice) {
    	return sysNoticeMapper.insertSysNotice(sysNotice);
    }
	
	
    /**
     * 修改
     *
     * @param sysNotice 修改的记录
     * @return 返回
     */
    public Integer updateSysNotice(SysNotice sysNotice) {
    	return sysNoticeMapper.updateSysNotice(sysNotice);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysNoticeById(Integer id) {
    	return sysNoticeMapper.deleteSysNoticeById(id);
    }
	
}