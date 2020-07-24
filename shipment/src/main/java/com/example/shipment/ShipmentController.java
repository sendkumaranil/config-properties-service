package com.example.shipment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Value("${app.shipment.dburl}")
    private String dbUrl;

    @Value("${app.shipment.dbusername}")
    private String dbUsername;

    @Value("${app.shipment.dbpassword}")
    private String dbPassword;

    @GetMapping("/db-connections")
    public String getDbConnections(){
        StringBuilder strDbConnectionBuilder=new StringBuilder("<table><th>DbConnection</th>");
        strDbConnectionBuilder.append("<tr><td>Url: </td><td>");
        strDbConnectionBuilder.append(dbUrl).append("</td></tr>");
        strDbConnectionBuilder.append("<tr><td>Username: </td><td>");
        strDbConnectionBuilder.append(dbUsername).append("</td></tr>");
        strDbConnectionBuilder.append("<tr><td>Password: </td><td>");
        strDbConnectionBuilder.append(dbPassword).append("</td></tr></table");
        return strDbConnectionBuilder.toString();
    }
}
