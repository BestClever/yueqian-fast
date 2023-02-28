package com.ityueqiangu.project.announcement.service;


import com.ityueqiangu.project.announcement.domain.Announcement;

import java.util.List;

/**
 * @author FlowerStone
 * @title: AnnouncementService
 * @description:
 * @date 2022-04-04 22:32:40
 */
public interface IAnnouncementService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<Announcement> selectAnnouncementList(Announcement announcement);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回Announcement
     */
    Announcement selectAnnouncementById(Integer id);
	
    /**
     * 新增
     *
     * @param announcement 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertAnnouncement(Announcement announcement);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param announcement 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateAnnouncement(Announcement announcement);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteAnnouncementById(Integer id);
	
}