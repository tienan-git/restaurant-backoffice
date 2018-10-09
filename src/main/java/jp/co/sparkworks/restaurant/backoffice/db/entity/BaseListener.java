package jp.co.sparkworks.restaurant.backoffice.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.slf4j.MDC;

import jp.co.sparkworks.restaurant.api.util.MDCUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseListener<T extends BaseEntity> implements EntityListener<T> {

	/**
	 * デフォルトのユーザID
	 */
	private static final String DEFAULT_USER_ID = "init";

	private static String getUserId() {
		Long userId = MDCUtil.getCustomerId();
		if (userId == null) {
			log.warn("UserId is NULL.");
			return DEFAULT_USER_ID;
		} else {
			return userId.toString();
		}
	}

	public BaseListener() {
	}

	@Override
	public void preInsert(T entity, PreInsertContext<T> context) {
		String userId = BaseListener.getUserId();
		LocalDateTime now = LocalDateTime.now();

		entity.setCreatedBy(userId);
		entity.setCreatedAt(now);
		entity.setUpdatedBy(userId);
		entity.setUpdatedAt(now);
		entity.setIsActived(1);
	}

	@Override
	public void preUpdate(T entity, PreUpdateContext<T> context) {
		String userId = BaseListener.getUserId();

		entity.setUpdatedBy(userId);
		entity.setUpdatedAt(LocalDateTime.now());
	}
}
