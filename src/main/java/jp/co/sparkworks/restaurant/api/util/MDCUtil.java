package jp.co.sparkworks.restaurant.api.util;

import org.slf4j.MDC;

/**
 * MDCを取得設定処理
 */
public class MDCUtil {

    MDCUtil() {
    }

    private static final String DEVICE_ID_KEY = "deviceId";
    private static final String CUSTOMER_ID_KEY = "customerId";

    public static String getDeviceId() {
        return MDC.get(DEVICE_ID_KEY);
    }

    public static void setDeviceId(String deviceId) {
        MDC.put(DEVICE_ID_KEY, deviceId);
    }

    public static Long getCustomerId() {
        String customerId = MDC.get(CUSTOMER_ID_KEY);
        if (customerId == null) {
            return null;
        } else {
            return Long.valueOf(customerId);
        }
    }

    public static void setCustomerId(Long customerId) {
        MDC.put(CUSTOMER_ID_KEY, customerId.toString());
    }

}
