package me.pgthinker.core.pojo;

import lombok.Data;

/**
 * @Project: me.pgthinker.core.pojo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:12
 * @Description:
 */
@Data
public class PageParam {

	private static final Integer PAGE_NO = 1;

	private static final Integer PAGE_SIZE = 10;

	public static final Integer PAGE_SIZE_NONE = -1;

	private Integer pageNo = PAGE_NO;

	private Integer pageSize = PAGE_SIZE;

}
