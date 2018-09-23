package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum YutaponType implements CodeEnum<String> {

    YELLOW("yellow", "イエロー"), 
    GREEN("green", "グリーン"), 
    PINK("pink", "ピンク"), 
    BLUE("blue", "ブルー"), 
    RED("red", "レッド");

    private YutaponType(String value, String label) {
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

    public static YutaponType of(String code) {
        return CodeEnum.of(YutaponType.class, code);
    }
}
