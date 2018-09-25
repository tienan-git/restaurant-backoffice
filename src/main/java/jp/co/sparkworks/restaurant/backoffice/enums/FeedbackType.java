package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * フィードバックタイプ 列挙クラス。
 *
 *
 */
public enum FeedbackType implements CodeEnum<String> {

    BUG("1", "不具合報告"), 
    ADVICE("2", "アドバイス"), 
    IDEA("3", "内容意見"), 
    PROBLEM("4", "抽選関連"), 
    OTHER("9", "その他");

    private FeedbackType(String value, String label) {
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

    public static FeedbackType of(String code) {
        return CodeEnum.of(FeedbackType.class, code);
    }
}
