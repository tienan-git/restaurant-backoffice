package jp.co.sparkworks.restaurant.api.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jp.co.sparkworks.restaurant.api.util.MDCUtil;
import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

/**
 * サービス層の開始・終了のトレース
 */
@Aspect
@Component
@Slf4j
public class ApiDeviceAspect {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerCustomDao customerCustomDao;

    @Around("within(jp.co.sparkworks.restaurant.api.controller.*)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String deviceId = request.getHeader("From");
        checkDevice(deviceId);

        if (!log.isTraceEnabled()) {
            return pjp.proceed();
        }

        log.trace("メソッド開始:{}\t引数:{}", pjp.getSignature(), Arrays.asList(pjp.getArgs()));
        final StopWatch sw = new StopWatch();
        sw.start();
        Object res;
        try {
            res = pjp.proceed();
        } catch (Throwable e) {
            sw.stop();
            log.trace("メソッド異常終了:{}\t処理時間:{}ミリ秒\t例外:{}", pjp.getSignature(), sw.getTotalTimeMillis(), e);
            throw e;
        }

        sw.stop();
        log.trace("メソッド終了:{}\t処理時間:{}ミリ秒\t戻り値:{}", pjp.getSignature(), sw.getLastTaskTimeMillis(), res);
        return res;
    }

    void checkDevice(String deviceId) {

        if (StringUtils.isEmpty(deviceId)) {
            throw new SystemException("deviceId cannot be NULL(Should be set in request header with name 'From')");
        }

        // 該当端末がDBに記録していなければ、記録しておく（すべての呼び出しがtryToCreateCustomer呼ばれないように、先に存在チェック）
        Customer customer = customerCustomDao.selectByDeviceId(deviceId);
        if (customer == null) {
            customer = tryToCreateCustomer(deviceId);
        }

        // MDCに保存する
        MDCUtil.setDeviceId(deviceId);
        MDCUtil.setCustomerId(customer.getCustomerId());
    }

    // 複数APIが同時に呼ばれる際、重複端末が登録されてしまう可能性がありますので、こちらスレード制御入れます
    synchronized Customer tryToCreateCustomer(String deviceId) {

        // 該当端末がDBに記録していなければ、記録しておく
        Customer customer = customerCustomDao.selectByDeviceId(deviceId);

        if (customer == null) {
            customer = new Customer();
            customer.setDeviceId(deviceId);
            customer.setNickName(null);
            customerDao.insert(customer);
        }

        return customer;
    }

}
