package jp.co.sparkworks.restaurant.api.util;

import org.slf4j.MDC;

/**
 * MDCを取得設定処理
 */
public class MDCUtil {

    MDCUtil() {
    }

    private static final String DEVICE_ID_KEY = "deviceId";

    public static String getDeviceId() {
        return MDC.get(DEVICE_ID_KEY);
    }

    public static void setDeviceId(String deviceId) {
        MDC.put(DEVICE_ID_KEY, deviceId);
    }

}
