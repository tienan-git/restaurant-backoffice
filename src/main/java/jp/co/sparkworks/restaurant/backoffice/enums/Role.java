package jp.co.sparkworks.restaurant.backoffice.enums;

/**
 * ロール 列挙クラス。
 *
 *
 */
public enum Role implements CodeEnum<Integer> {

	VIEWER(1, "照会のみ"), ADMIN(9, "管理者");

	private Role(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	/** 値 */
	private Integer value;

	/** 名称 */
	private String label;

	public String getLabel() {
		return label;
	}

	public Integer getValue() {
		return value;
	}

	public static Role of(Integer long1) {
		return CodeEnum.of(Role.class, long1);
	}

}
