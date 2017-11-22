package com.yougou;

import com.belle.finance.biz.dubbo.IReturnDebtDubboService;
import com.belle.finance.common.model.vo.QueryRefundInfoParamsVo;
import com.belle.finance.common.model.vo.RefundInfoResultVo;
import com.yougou.common.BaseTestCase;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App extends BaseTestCase
{
    @Test
    public  void testReturnDebtDubboService() throws Exception{
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"applicationContext-api.xml"});
        IReturnDebtDubboService returnDebtDubboService =
                (IReturnDebtDubboService) applicationContext.getBean("returnDebtDubboService");
        QueryRefundInfoParamsVo paramsVo = new QueryRefundInfoParamsVo();
        paramsVo.setStartRefundDate(DateUtil.parseDate("2017-11-14 00:00:00",DateUtil.LONG_DATE_TIME_PATTERN));
        paramsVo.setEndRefundDate(DateUtil.parseDate("2017-11-14 23:00:00",DateUtil.LONG_DATE_TIME_PATTERN));
        paramsVo.setPage(3);
        paramsVo.setPageSize(10);
        RefundInfoResultVo refundInfoResultVo = returnDebtDubboService.queryRefundInfoToMerchant(paramsVo);
        System.out.println(refundInfoResultVo.isResultFlag());
        System.out.println(refundInfoResultVo.getResultMsg());
        System.out.println(refundInfoResultVo.getRefundInfoList());
        System.out.println(refundInfoResultVo.getTotalCount());
    }
}
