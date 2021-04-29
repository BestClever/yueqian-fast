package com.ityueqiangu.core.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 树类*/
public class DTree implements Serializable {

	/** 节点ID*/
	private String id;
	
	/** 上级节点ID*/
	private String parentId;
	
	/** 节点名称*/
	private String title;
	
	/** 是否最后一级节点*/
	private Boolean isLast;
	
	/** 自定义图标class*/
	private String iconClass;
	
	/** 表示用户自定义需要存储在树节点中的数据*/
	private Object basicData;
	
	/** 复选框集合*/
//	private List<CheckArr> checkArr = new ArrayList<CheckArr>();
	private String checkArr = "0";

	
	/** 子节点集合*/
	private List<DTree> children = new ArrayList<DTree>();

	public DTree() {}

	public DTree(String id, String parentId, String title) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.isLast = isLast;
	}

	public DTree(String id, String parentId, String title, String checkArr) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.checkArr = checkArr;
	}
	
	public DTree(String id, String parentId, String title, Boolean isLast) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.isLast = isLast;
	}

	public DTree(String id, String parentId, String title, String iconClass,
                 Object basicData, String checkArr) {
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.iconClass = iconClass;
		this.basicData = basicData;
		this.checkArr = checkArr;
	}

	public DTree(String id, String parentId, String title,
                 Boolean isLast, String iconClass, Object basicData,
				 String checkArr, List<DTree> children) {
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.isLast = isLast;
		this.iconClass = iconClass;
		this.basicData = basicData;
		this.checkArr = checkArr;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public Object getBasicData() {
		return basicData;
	}

	public void setBasicData(Object basicData) {
		this.basicData = basicData;
	}

	public String getCheckArr() {
		return checkArr;
	}

	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}

	public List<DTree> getChildren() {
		return children;
	}

	public void setChildren(List<DTree> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "DTree [id=" + id + ", parentId=" + parentId + ", title="
				+ title + ", isLast=" + isLast + ", iconClass=" + iconClass
				+ ", basicData=" + basicData + ", checkArr=" + checkArr
				+ ", children=" + children + "]";
	}
	
}