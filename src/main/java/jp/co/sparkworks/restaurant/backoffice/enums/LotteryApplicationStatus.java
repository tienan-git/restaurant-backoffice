package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ゆたぽんタイプ 列挙クラス。
 *
 *
 */
public enum LotteryApplicationStatus implements CodeEnum<String> {

	NOAPPLY("0", "未応募"), 
	APPLIED("1", "応募済み"), 
	BINGO("2", "あたり！"), 
    SORRY("3", "ハズレ");

	private LotteryApplicationStatus(String value, String label) {
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

	public static LotteryApplicationStatus of(String code) {
		return CodeEnum.of(LotteryApplicationStatus.class, code);
	}
}
