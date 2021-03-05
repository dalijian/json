package com.lijian.jmx;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class PersonAgent {
    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException, InterruptedException {

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName personName = new ObjectName("jmxBean:name=xiaoming");
        server.registerMBean(new PersonDynamic(new Person("xiaoming", 27)), personName);
        TimeUnit.MINUTES.sleep(60);

    }
}
