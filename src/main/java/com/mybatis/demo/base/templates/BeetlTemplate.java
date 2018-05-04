package com.mybatis.demo.base.templates;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/03 16:51
 */

@Component
public class BeetlTemplate {

    @Resource
    private BeetlTplRender beetlTplRender;

    public String render(Map map, String tplPath) {
        return beetlTplRender.renderPacketTpl(map, tplPath);
    }

}
