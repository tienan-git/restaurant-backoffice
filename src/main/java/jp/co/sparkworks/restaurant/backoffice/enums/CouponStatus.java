package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum CouponStatus implements CodeEnum<String> {

    ENABLE("0", "利用可"), 
    USED("1", "利用済み"), 
    DISABLE("2", "利用不可(上書きされ)");

    private CouponStatus(String value, String label) {
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

    public static CouponStatus of(String code) {
        return CodeEnum.of(CouponStatus.class, code);
    }
}
