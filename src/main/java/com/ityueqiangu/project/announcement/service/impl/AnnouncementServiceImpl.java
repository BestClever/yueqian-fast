package com.ityueqiangu.project.announcement.service.impl;

import com.ityueqiangu.project.announcement.domain.Announcement;
import com.ityueqiangu.project.announcement.mapper.AnnouncementMapper;
import com.ityueqiangu.project.announcement.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FlowerStone
 * @title: AnnouncementServiceImpl
 * @description:
 * @date 2022-04-04 22:32:40
 */
@Service
public class AnnouncementServiceImpl implements IAnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<Announcement> selectAnnouncementList(Announcement announcement) {
       return announcementMapper.selectAnnouncementList(announcement);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public Announcement selectAnnouncementById(Integer id) {
    	return announcementMapper.selectAnnouncementById(id);
    }
	
    /**
     * 新增
     *
     * @param announcement 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertAnnouncement(Announcement announcement) {
    	return announcementMapper.insertAnnouncement(announcement);
    }
	
	
    /**
     * 修改
     *
     * @param announcement 修改的记录
     * @return 返回
     */
    public Integer updateAnnouncement(Announcement announcement) {
    	return announcementMapper.updateAnnouncement(announcement);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteAnnouncementById(Integer id) {
    	return announcementMapper.deleteAnnouncementById(id);
    }
	
}