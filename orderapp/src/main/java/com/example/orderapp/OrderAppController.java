package com.example.orderapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/orderapp")
public class OrderAppController {
    @Value("${message:Hello default}")
    private String message;

    @Value("${app.order.dburl}")
    private String dbUrl;

    @Value("${app.order.dbpassword}")
    private String dbPassword;

    @Value("${app.order.dbusername}")
    private String dbUsername;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @GetMapping("/db-connections")
    String getDBConnectionDetails(){

        StringBuilder dbConStrBldr=new StringBuilder("<table><th>DbConnection: </th>");
        dbConStrBldr.append("<tr><td>Url: </td>").append("<td>").append(dbUrl).append("</td></tr>");
        dbConStrBldr.append("<tr><td>Username: </td>").append("<td>").append(dbUsername).append("</td></tr>");
        dbConStrBldr.append("<tr><td>Password: </td>").append("<td>").append(dbPassword).append("</td></tr>");
        dbConStrBldr.append("</table>");

        return dbConStrBldr.toString();
    }
}
