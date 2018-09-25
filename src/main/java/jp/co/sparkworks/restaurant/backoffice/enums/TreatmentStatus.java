package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum TreatmentStatus implements CodeEnum<String> {

    INIT("0", "未処理"), 
    DONE("1", "処理済み");

    private TreatmentStatus(String value, String label) {
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

    public static TreatmentStatus of(String code) {
        return CodeEnum.of(TreatmentStatus.class, code);
    }
}
