package com.clay.h2.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author clay
 * @date 2023-05-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	private Integer code = 0;

	private String msg;

	private T data;

	public Result(T data) {
		this.data = data;
	}

}
