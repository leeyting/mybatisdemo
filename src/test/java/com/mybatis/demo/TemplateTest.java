package com.mybatis.demo;

import com.mybatis.demo.base.templates.BeetlTemplate;
import com.mybatis.demo.base.utils.DateUtil;
import com.mybatis.demo.base.utils.MyJSONArray;
import com.mybatis.demo.base.utils.MyJSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/03 09:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class TemplateTest {

    @Autowired
    private BeetlTemplate beetlTemplate;


    @Test
    public void render() {
      /*  JSONObject jsonObject = new JSONObject();
        jsonObject.put("requestDate", DateUtil.getStrNow());
        jsonObject.put("userCode", "test");
        jsonObject.put("password", "123#21");
        jsonObject.put("transNo", "15445");
        jsonObject.put("contSource", "0123");
        jsonObject.put("agentCom", "4556");

        JSONArray risks = new JSONArray();
        JSONObject risk = new JSONObject();
        risk.put("riskCode", "111052");
        risk.put("mainRiskCode", "111000");
        risk.put("amnt", "500000");
        risk.put("payIntv", "1");
        risk.put("insuYearFlag", "10");
        risk.put("insuYear", "2");
        risk.put("payendyear", "2");

        JSONArray dutys = new JSONArray();
        JSONObject duty1 = new JSONObject();
        duty1.put("dutyCode", "111");
        duty1.put("amnt", "100000");
        duty1.put("prem", "121");
        duty1.put("payIntv", "1");
        duty1.put("insuYearFlag", "10");
        duty1.put("insuYear", "2");
        duty1.put("payendyear", "2");
        dutys.add(duty1);

        JSONObject duty2 = new JSONObject();
        duty2.put("dutyCode", "111");
        duty2.put("amnt", "100000");
        duty2.put("prem", "121");
        duty2.put("payIntv", "1");
        duty2.put("insuYearFlag", "10");
        duty2.put("insuYear", "2");
        duty2.put("payendyear", "2");
        dutys.add(duty2);

        risk.put("dutys", dutys);
        risks.add(risk);
        jsonObject.put("risks", risks);
        Map map = new HashMap();
        map.put("data", jsonObject);
        String render = beetlTemplate.render(map, "/test.beetl");
        System.out.println(render);*/

        MyJSONObject data = new MyJSONObject("data")
                .put("requestDate", DateUtil.getStrNow()).put("userCode", "test")
                .put("password", "123#21").put("transNo", "15445")
                .put("contSource", "0123").put("agentCom", "4556");

        data.put("risks", new MyJSONArray("risks")
                .addObj(new MyJSONObject("risk")
                .put("riskCode", "111052").put("mainRiskCode", "111000")
                .put("amnt", "500000").put("payIntv", "1")
                .put("insuYearFlag", "10").put("insuYear", "2").put("payendyear", "2")
                .put("dutys", new MyJSONArray("dutys")
                        .addObj(new MyJSONObject("duty")
                                .put("dutyCode", "111").put("amnt", "100000")
                                .put("prem", "121").put("payIntv", "1")
                                .put("insuYearFlag", "10").put("insuYear", "2")
                                .put("payendyear", "2").put("payendyearflag", "10"))
                        .addObj(new MyJSONObject("duty")
                                .put("dutyCode", "111").put("amnt", "100000")
                                .put("prem", "121").put("payIntv", "1")
                                .put("insuYearFlag", "10").put("insuYear", "2")
                                .put("payendyear", "2").put("payendyearflag", "10"))))
                .addObj(new MyJSONObject("risk")
                        .put("riskCode", "111052").put("mainRiskCode", "111000")
                        .put("amnt", "500000").put("payIntv", "1")
                        .put("insuYearFlag", "10").put("insuYear", "2").put("payendyear", "2")
                        .put("dutys", new MyJSONArray("dutys")
                                .addObj(new MyJSONObject("duty")
                                        .put("dutyCode", "111").put("amnt", "100000")
                                        .put("prem", "121").put("payIntv", "1")
                                        .put("insuYearFlag", "10").put("insuYear", "2")
                                        .put("payendyear", "2").put("payendyearflag", "10"))
                                .addObj(new MyJSONObject("duty")
                                        .put("dutyCode", "111").put("amnt", "100000")
                                        .put("prem", "121").put("payIntv", "1")
                                        .put("insuYearFlag", "10").put("insuYear", "2")
                                        .put("payendyear", "2").put("payendyearflag", "10"))
                        )));

        Map map = new HashMap();
        map.put("data", data);
        String render = beetlTemplate.render(map, "/test.beetl");
        System.out.println(render);

    }
}
