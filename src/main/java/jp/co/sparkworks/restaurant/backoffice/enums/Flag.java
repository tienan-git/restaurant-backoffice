package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum Flag implements CodeEnum<String> {

	ON("1", "有効"), OFF("0", "無効");

	private Flag(String value, String label) {
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

	public static Flag of(String code) {
		return CodeEnum.of(Flag.class, code);
	}
}
