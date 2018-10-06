package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * 店舗ステータス 列挙クラス。
 *
 *
 */
public enum RestaurantStatus implements CodeEnum<String> {

    ON("1", "連携"), 
    OFF("0", "未連携");

    private RestaurantStatus(String value, String label) {
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

    public static RestaurantStatus of(String code) {
        return CodeEnum.of(RestaurantStatus.class, code);
    }
}
