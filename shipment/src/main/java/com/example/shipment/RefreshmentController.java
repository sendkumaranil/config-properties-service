package com.example.shipment;

import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshmentController {

    private RefreshEndpoint refreshEndpoint;

    public RefreshmentController(RefreshEndpoint refreshEndpoint){
        this.refreshEndpoint=refreshEndpoint;
    }

    @GetMapping("/refresh")
    public String refreshOrderApp(){
        refreshEndpoint.refresh();
        return "Shipment Property Refreshed Successfully";
    }
}
