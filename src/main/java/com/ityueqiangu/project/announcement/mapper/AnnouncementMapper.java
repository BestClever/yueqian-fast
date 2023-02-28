package com.ityueqiangu.project.announcement.mapper;


import com.ityueqiangu.project.announcement.domain.Announcement;

import java.util.List;

/**
 * @author FlowerStone
 * @title: AnnouncementMapper
 * @description:
 * @date 2022-04-04 22:32:40
 */
public interface AnnouncementMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Announcement> selectAnnouncementList(Announcement announcement);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Announcement selectAnnouncementById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param announcement 新增的记录
     * @return 返回影响行数
     */
	Integer insertAnnouncement(Announcement announcement);
	
	
	/**
     * 修改
     *
     * @param announcement 修改的记录
     * @return 返回影响行数
     */
	Integer updateAnnouncement(Announcement announcement);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteAnnouncementById(Integer id);
    
    /**
     * 根据条件删除记录
     *
     * @param announcement}
     * @return 返回影响行数
     */
	Integer deleteAnnouncementByCondition(Announcement announcement);
	
}