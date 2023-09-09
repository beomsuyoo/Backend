package org.delivery.api.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.enums.StoreCategory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
public class StoreOpenApiController {
    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public Api<StoreResponse> register(
            @Valid
            @RequestBody Api<StoreRegisterRequest> request
    ){
        var response = storeBusiness.register(request.getBody());
        return Api.OK(response);
    }
}
