package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum CouponHoldStatus implements CodeEnum<String> {

    ENABLE("0", "追加済み"), 
    USED("1", "利用済み");

    private CouponHoldStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /** 値 */
    private String value;

    /** 名称 */
    private String label;

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public static CouponHoldStatus of(String code) {
        return CodeEnum.of(CouponHoldStatus.class, code);
    }
}
