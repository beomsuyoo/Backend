package org.delivery.api.domain.userorder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDetailResponse {
    // order
    private UserOrderResponse userOrderResponse;
    // store
    private StoreResponse storeResponse;
    // ordered menus
    private List<StoreMenuResponse> storeMenuResponseList;

}
