package com.mybatis.demo.base.templates;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

public class StringPrinter implements Function {
	public String call(Object[] paras, Context ctx) {
		if (paras == null || paras.length != 2) {
			return "";
		}

		String input = (String) paras[0];
		String defaultOuput = (String) paras[1];
		if (StringUtils.isEmpty(input)) {
			if (defaultOuput == null) {
				return "";
			} else {
				return defaultOuput;
			}
		} else {
			return input;
		}
	}
}
