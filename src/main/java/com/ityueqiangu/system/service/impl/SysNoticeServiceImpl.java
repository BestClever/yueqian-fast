package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.system.domain.SysNotice;
import com.ityueqiangu.system.mapper.SysNoticeMapper;
import com.ityueqiangu.system.service.ISysNoticeService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Clever、xia
 * @title: SysNoticeServiceImpl
 * @description:
 * @date 2021-05-08 16:38:48
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