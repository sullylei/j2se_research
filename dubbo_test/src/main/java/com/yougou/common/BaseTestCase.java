package com.yougou.common;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Creator: lei.s
 * Create Date: 2017年08月22日
 * 类功能描述：
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-api.xml"})
public class BaseTestCase {
    
    @Test
    public void  test(){
        System.out.print("调试===========");
    }
    
}
