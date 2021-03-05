### 使用 java-agent 
####JVM启动前静态Instrument
1. `Manifest-Version: 1.0
 Premain-Class: com.rickiyang.learn.PreMainTraceAgent
 Can-Redefine-Classes: true
 Can-Retransform-Classes: true`
    1. Premain-Class 指定 在 main 方法 启动 之前 指定 类 
    
###JVM启动后动态Instrument
1. `Manifest-Version: 1.0
    Can-Redefine-Classes: true
    Can-Retransform-Classes: true
    Agent-Class: com.rickiyang.learn.AgentMainTest`
    2. Agent-Class 指定 agentmain 方法
 
