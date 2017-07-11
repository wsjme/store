package domain;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable
{
	// 总条数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 当前页
	private int pageNumber;
	// 每页显示的条数
	private int pageSize;
	// 每页显示的数据
	private List<T> list;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
