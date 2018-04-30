package com.mybatis.demo.base.utils;

import com.mybatis.demo.base.exception.ParamAssertException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/30 22:55
 */
public class ParamAssert {
	public static void assertTrue(boolean value, String errMsg) throws ParamAssertException {
		if (!value) {
			throw new ParamAssertException(errMsg);
		}
	}

	/**
	 * @param v
	 * @param empty 是否可以为空，true表示可以为空
	 * @param minSize 最短长度，null不检测
	 * @param maxSize 最长长度， null不检测
	 * @param errMsg 错误提示
	 * @throws ParamAssertException
	 */
	public static void assertStr(String v, boolean empty, Integer minSize, Integer maxSize, String errMsg)
			throws ParamAssertException {
		if (StringUtils.isEmpty(v)) {
			if (!empty) {
				throw new ParamAssertException(errMsg);
			}
		} else {
			if (minSize != null) {
				if (v.length() < minSize.intValue()) {
					throw new ParamAssertException(errMsg);
				}
			}

			if (maxSize != null) {
				if (v.length() > maxSize.intValue()) {
					throw new ParamAssertException(errMsg);
				}
			}
		}

	}
}
