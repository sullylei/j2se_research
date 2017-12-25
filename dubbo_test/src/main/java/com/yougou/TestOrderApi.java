package com.yougou;

import com.yougou.ordercenter.api.order.IOrderApi;
import com.yougou.ordercenter.model.order.OrderSourceGroup;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Creator: lei.s
 * Create Date: 2017年11月24日-18:14
 *
 * @Description:
 */
public class TestOrderApi {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"applicationContext-api.xml"});
        IOrderApi orderApi =
                (IOrderApi) applicationContext.getBean("orderApiService");
        List<OrderSourceGroup> orderSourceGroupList = orderApi.getOrderSourceGroupListByTypeAndLevel(1,1);
        System.out.println(orderSourceGroupList);

    }
}
