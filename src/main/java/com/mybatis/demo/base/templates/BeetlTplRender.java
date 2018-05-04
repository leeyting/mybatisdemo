package com.mybatis.demo.base.templates;

import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class BeetlTplRender {

    private Logger logger = LoggerFactory.getLogger(BeetlTplRender.class);
    private Configuration cfg = null;
    private GroupTemplate groupTemplate = null;

    public BeetlTplRender() throws IOException {
        this.cfg = Configuration.defaultConfiguration();
        this.groupTemplate = new GroupTemplate(new ClasspathResourceLoader("templates"), this.cfg);
        this.groupTemplate.registerFunction("StringPrinter", new StringPrinter());
    }

    public String renderPacketTpl(Map map, String tplPath) {
        if (StringUtils.isEmpty(tplPath)) {
            return null;
        }

        this.logger.info("报文模板：{}", tplPath);

        long beginMs = System.currentTimeMillis();
        try {
            Template template = this.groupTemplate.getTemplate(tplPath);

            logger.info("模板参数：{}", map.toString());

            template.binding(map);
            return template.render();
        } catch (Exception e) {
            logger.error("报文渲染失败", e);
            return null;
        } finally {
            this.logger.info("报文渲染耗时：{} 毫秒", (System.currentTimeMillis() - beginMs));
        }
    }
}
