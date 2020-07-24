# config-properties-service
This is Config Property service by using config server of spring config cloud

# Agenda
When changes to the property then no need to build the dependent client application.
<p>Use Centralized property Repository in order to manage properties</p>

# Use Case
<h5>config-property-service<h5>
<p> Spring Cloud Config provides server-side and client-side support for externalized configuration in a distributed system. With the Config Server, you have a central place to manage external properties for applications across all environments. The concepts on both client and server map identically to the Spring Environment and PropertySource abstractions</p>
<p>The default strategy for locating property sources is to clone a git repository at spring.cloud.config.server.git.uri , this is basically the Github property repository but in this example we are using local git file system instead github remote repos</p>

<p>We need to add the following spring boot dependency<p>
 <pre>
          <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
          </dependency>
 </pre>
 <p> We need to add the following property in the application.property of configuration-service</p>
 <pre>
          server.port=9191
          #below is the git repository directory or it could be github repos url
          spring.cloud.config.server.git.uri=${HOME}/my_properties
          #Note: here my home dir is /Users/anilkumar/
 </pre>
 <h5> Create a new directory my_properties under the path ${HOME} and run the command git init </h5>
 <p>Create new property file of dependent app or service (here I have a dependent service is orderapp)</p>
 <p>touch orderapp.properties</p>
 <p>add the properties of the orderapp service and commit the property file</p>
 <p>Below is the screen shot for the terminal command</p>
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/property-repos-terminal.png)
 
 <h5>orderapp service</h5>
 <p>This is independent different service or app which will have its own related properties</p>
 <p>We do not keep its own properties at app level rather we will keep at centralized level inside my_properties directory</p>
 <p>orderapp will invoke those its own property from the centralized repos</p>
 <p>we will add two property files to the orderapp service one for app level (application.properties) and other for cloud level (bootstrap.properties)</p>
 <p>we will use following lines to the bootstrap.properties file</p>
 <pre>
      #this is the app name which is the same name of centralized property name
      spring.application.name=orderapp
      #this is the configuration service url
      spring.cloud.config.uri=http://localhost:9191
 </pre>
 <p>We will add actuator and cloud config dependencies to the orderapp pom file</p>
 <pre>```
 
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
          </dependency>
 </pre>
 <p>Now we will add following line to the application.properties</p>
 <pre>
    #this is actuator property, in real scenarios we cannot provide * here for security reason
    management.endpoints.web.base-path=/orderapp
    management.endpoint.refresh.enabled=true
    management.endpoints.web.exposure.include=health,info,refresh
 </pre>
 
 <h5>shipment service (similarly of orderapp)</h5>
 
 <p>Start the configuration-service </p>
 <p>Start the orderapp service</p>
 <p>Start the shipment service</p>
 
 <p>Below is the screen shot of before property change</p>
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/order-app-dbconnections-beforechange.png)
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/shipment-url.png)
 
 <p>Now change the orderapp db password in the centralized property repos below is the screen shot</p>
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/change-orderapp-dbpassword.png)
 
 <p>Now after change the centralized properties, we need to refresh the orderapp and we will do by actuator/refresh</p>
 <p>Below is the screen shot which refresh the orderapp by POST method using postman</p>
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/refresh-orderapp-by-POST-postman.png)
 
 OR
 <p> Run the below url to refresh the orderapp </p>
 <p> http://localhost:8080/refresh </p>
 
 <p>After changes the property No need to restart the configuration-service and orderapp service just hit the orderapp service url</p>
 <p>Below is the screen shots of the after property changes</p>
 
 ![alt tag](https://github.com/sendkumaranil/config-properties-service/blob/master/after-password-change.png)
 
 
