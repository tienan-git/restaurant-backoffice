package jp.co.sparkworks.restaurant.backoffice.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.Yutapon;
import lombok.Data;

@Data
public class SynchronizationReq {
    private String deviceId;
    private List<Yutapon> data;
}
