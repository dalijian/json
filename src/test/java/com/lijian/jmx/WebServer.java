package com.lijian.jmx;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class WebServer implements WebServerMBean {



    public static void main(String[]args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        WebServer ws = new WebServer();
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(ws, new ObjectName("myapp:type=webserver,name=Port 8080"));
        Thread.sleep(600000);
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getLogLevel() {
        return null;
    }

    @Override
    public void setLogLevel(String level) {

    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public void start() {

    }
}
 




