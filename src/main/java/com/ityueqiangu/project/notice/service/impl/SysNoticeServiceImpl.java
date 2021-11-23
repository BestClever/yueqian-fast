package com.ityueqiangu.project.notice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ityueqiangu.common.utils.PrimaryKeyUtils;
import com.ityueqiangu.common.utils.UserUtil;
import com.ityueqiangu.project.notice.domain.SysNotice;
import com.ityueqiangu.project.notice.mapper.SysNoticeMapper;
import com.ityueqiangu.project.notice.service.ISysNoticeService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysNoticeServiceImpl
 * @description:
 * @date 2021-11-22 15:27:11
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
         List<SysNotice> sysNotices = sysNoticeMapper.selectSysNoticeList(sysNotice);
         return sysNotices;
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
        sysNotice.setInfoCode(PrimaryKeyUtils.generateOrderNo());
        sysNotice.setPublishBy(UserUtil.getCurrentUser().getUserId());
        sysNotice.setPublishTime(DateUtil.date());
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

    /**
     * 获取单个实体
     * @author FlowerStone
     * @date 2021年11月23日 0023 16:35:38
     * @param sysNoticeParam
     * @return
     */
    @Override
    public SysNotice getOne(SysNotice sysNoticeParam) {
        return sysNoticeMapper.getOne(sysNoticeParam);
    }

}