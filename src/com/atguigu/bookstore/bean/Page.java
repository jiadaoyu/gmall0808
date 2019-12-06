package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 * 		每个分页都是分页类的一个实例
 * @author Administrator
 *
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分页对象对应的路径
	 */
	private String  path;
	/**
	 * 分页对象的图书集合
	 */
	private List<T> data;
	/**
	 * 总页码：   计算得到
	 */
	private int totalPage;
	/**
	 * 记录总条数：查询数据库表得到
	 */
	private int totalCount;
	/**
	 * 每页显示记录条数： 系统设置的
	 */
	private int size;
	/**
	 * 每页数据查询的起始索引： 计算得到
	 */
	private int index;
	/**
	 * 每页页码：  用户传入
	 */
	private int pageNumber;
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * 计算得到：
	 * 		totalCount    size    totalPage
	 * 			10			3		4  = 10/3+1
	 * 			10 			5		2	= 10/5
	 * 			10 			11		1	= 10/11+1
	 * @return
	 */
	public int getTotalPage() {
		if(totalCount%size==0) {
			totalPage = totalCount/size;
		}else {
			totalPage = totalCount/size +1;
		}
		return totalPage;
	}
/*	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getIndex() {
		index = (getPageNumber()-1)*size;
		return index;
	}
	/*public void setIndex(int index) {
		this.index = index;
	}*/
	//获取pageNumber时，检查是否超出范围
	public int getPageNumber() {
		if(pageNumber<1) {
			pageNumber = 1;
		}else if(pageNumber>getTotalPage()){
			pageNumber = getTotalPage();
		}
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Page(List<T> data, int totalPage, int totalCount, int size, int index, int pageNumber) {
		super();
		this.data = data;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.size = size;
		this.index = index;
		this.pageNumber = pageNumber;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Page [data=" + data + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", size=" + size
				+ ", index=" + index + ", pageNumber=" + pageNumber + "]";
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
