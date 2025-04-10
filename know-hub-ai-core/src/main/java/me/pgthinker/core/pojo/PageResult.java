package me.pgthinker.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2025/4/9 17:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

	private long total;

	private List<T> records;

	private long current;

	private long size;

	private long pages;

}
