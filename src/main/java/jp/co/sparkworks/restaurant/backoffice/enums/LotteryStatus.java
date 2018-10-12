package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum LotteryStatus implements CodeEnum<String> {

	INIT("0", "未抽選"), 
	DONE("1", "抽選済み");

	private LotteryStatus(String value, String label) {
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

	public static LotteryStatus of(String code) {
		return CodeEnum.of(LotteryStatus.class, code);
	}
}
