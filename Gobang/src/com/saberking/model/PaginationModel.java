package com.saberking.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 封装分页相关参数的封装类
 * 
 * @Author: Saber污妖王
 * @Date: 2019/9/22
 * @Description: com.saberking.model
 * @version:1.0
 */
@Data
public class PaginationModel<T> {
	/**
	 * 查询出来的数据列表集合
	 */
	private List<T> data;
	/**
	 * 是否显示前一页按钮
	 */
	private boolean showPrevious;
	/**
	 * 是否显示第一页按钮
	 */
	private boolean showFirstPage;
	/**
	 * 是否显示下一页按钮
	 */
	private boolean showNext;
	/**
	 * 是否显示最后一页按钮
	 */
	private boolean showEndPage;
	/**
	 * 当前页页码
	 */
	private Integer page;
	/**
	 * 分页导航栏中间页码列表中的当前页，显示其前后多少个，默认3个
	 */
	private static int pagesNum = 3;
	/**
	 * 保证分页导航栏中间可以点击的页码列表， 列表大小跟 pagesNum 有关，这里 pagesNum 为3，即取前后3页， 所以默认最多7个，最少1个
	 */
	private List<Integer> pages = new ArrayList<>();
	/**
	 * 向数据库中分页查询的时候的开始索引
	 */
	private Integer start;
	/**
	 * 向数据库中分页查询的时候的每次查询的条数，即一页有多少条数据
	 */
	private Integer size;
	/**
	 * 总共有多少条数据
	 */
	private Integer totals;
	/**
	 * 总共有多少页
	 */
	private Integer totalPage;

	/**
	 * 初始化分页相关参数的方法
	 * 
	 * @param totals 总共有多少条数据
	 * @param page   当前页页码
	 * @param size   每页条数
	 */
	public void setPagination(Integer totals, Integer page, Integer size) {
		/**
		 * 初始化当前封装类的数据总条数和每页条数
		 */
		this.totals = totals;
		/**
		 * 保证每页条数是正整数，如果不是默认每页10条
		 */
		if (size > 0) {
			this.size = size;
		} else {
			this.size = 10;
		}

		/**
		 * 初始化总页数
		 */
		if (totals % size == 0 && totals != 0) {
			/**
			 * 如果数据总条数能被每页条数整除且数据总条数不位0，那么总页数就等于他们的商
			 */
			this.totalPage = totals / size;
		} else if (totals > size) {
			/**
			 * 如果数据总条数不能被每页条数整除且总条数大于每页条数，那么总页数就等于他们的商+1
			 */
			this.totalPage = totals / size + 1;
		} else {
			/**
			 * 如果数据总条数小于每页条数包括0，那么总页数就等于1
			 */
			this.totalPage = 1;
		}

		/**
		 * 初始化当前页页码
		 */
		if (page <= 0) {
			/**
			 * 保证当前页页码是正整数，如果不是则为1
			 */
			this.page = 1;
		} else if (page > this.totalPage) {
			/**
			 * 保证当前页页码不大于总页数，如果不是则默认为最后一页
			 */
			this.page = this.totalPage;
		} else {
			this.page = page;
		}

		/**
		 * 向分页查询的开始索引，公式：开始索引 = (页码 - 1) * 每页 条数
		 */
		this.start = (this.page - 1) * size;

		/**
		 * 先将当前页页码加入
		 */
		pages.add(page);
		/**
		 * 初始化分页导航栏中间可以点击的页码列表
		 */
		for (int i = 1; i <= pagesNum; i++) {
			if (page - i > 0) {
				/**
				 * 将当前页的前三页加入，并保证如果已经加到第一页则不再加入
				 */
				pages.add(0, page - i);
			}
			if (page + i <= this.totalPage) {
				/**
				 * 将当前页的后三页加入，并保证如果已经加到最后一页则不再加入
				 */
				pages.add(page + i);
			}
		}

		/**
		 * 是否展示上一页
		 */
		if (page == 1) {
			/**
			 * 如果当前页是第一页则不展示，反之则展示
			 */
			showPrevious = false;
		} else {
			showPrevious = true;
		}

		/**
		 * 是否展示下一页
		 */
		if (page == this.totalPage) {
			/**
			 * 如果当前页是最后一页则不展示，反之则展示
			 */
			showNext = false;
		} else {
			showNext = true;
		}

		/**
		 * 是否展示第一页
		 */
		if (pages.contains(1)) {
			/**
			 * 如果分页导航栏中间的页码列表里有第一页则不展示，反之则展示
			 */
			showFirstPage = false;
		} else {
			showFirstPage = true;
		}

		/**
		 * 是否展示最后一页
		 */
		if (pages.contains(this.totalPage)) {
			/**
			 * 如果分页导航栏中间的页码列表里有最后页则不展示，反之则展示
			 */
			showEndPage = false;
		} else {
			showEndPage = true;
		}
	}
}
