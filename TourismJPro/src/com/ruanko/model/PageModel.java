package com.ruanko.model;

import java.util.List;

/**
 * 分页实体 保存分页的相关数据
 * 
 * @author rkcoe
 */
public class PageModel {

	private int size; // 每页显示多少条记录
	private int page; // 希望显示第几页 当前页
	private int totalPage; // 一共有多少页
	private int totalCount; // 一共有多少条记录
	private int pre; // 前一页开始记录
	private int next; // 后一页开始页面
	// 查询数据库
	private List list; // 本页的数据列表

	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）

	/**
	 * 计算一共多少页
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param pageSize
	 *            每页记录数
	 */
	public PageModel(int totalCount, int pageSize, int page) {
		this.size = pageSize;
		this.totalCount = totalCount;
		this.page = page;
		if (this.totalCount % this.size == 0) {
			this.totalPage = this.totalCount / this.size;
		} else {
			this.totalPage = this.totalCount / this.size + 1;
		}

		this.pre = page - 1;
		if (pre <= 1) {
			pre = 1;
		}
		this.next = page + 1;
		if (next >= totalPage) {
			next = totalPage;
		}

		// 计算 beginPageIndex 与 endPageIndex
		// >> 总页码小于等于10页时，全部显示
		if (totalPage <= 10) {
			if (totalPage == 0) {
				totalPage = 1;
				beginPageIndex = 1;
				endPageIndex = totalPage;
				;
			} else {
				beginPageIndex = 1;
				endPageIndex = totalPage;
			}

		}
		// >> 总页码大于10页时，就只显示当前页附近的共10个页码
		else {
			// 默认显示 前4页 + 当前页 + 后5页
			beginPageIndex = page - 4; // 7 - 4 = 3;
			endPageIndex = page + 5; // 7 + 5 = 12; --> 3 ~ 12

			// 如果前面不足4个页码时，则显示前10页
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 如果后面不足5个页码时，则显示后10页
			else if (endPageIndex > totalPage) {
				endPageIndex = totalPage;
				beginPageIndex = totalPage - 9;
			}
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
