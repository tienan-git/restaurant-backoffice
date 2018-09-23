package jp.co.sparkworks.restaurant.backoffice.db.entity;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Version;

@Entity(listener = BaseListener.class)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BaseEntity {
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "updated_by")
    String updatedBy;
    @Column(name = "is_actived")
    Integer isActived;
    @Version
    @Column(name = "version_no")
    Long versionNo;
}
