1. log4j 线程阻塞 
    1.  public
         void callAppenders(LoggingEvent event) {
           int writes = 0;
       
           for(Category c = this; c != null; c=c.parent) {
             // Protected against simultaneous call to addAppender, removeAppender,...
             synchronized(c) {
       	if(c.aai != null) {
       	  writes += c.aai.appendLoopOnAppenders(event);
       	}
       	if(!c.additive) {
       	  break;
       	}
             }
           }
       
           if(writes == 0) {
             repository.emitNoAppenderWarning(this);
           }
         }
    2. 锁主了 category ,和 category.parent ; 只要 配置了 additive = false,就不会 锁住 parent
    3. com.c 锁住了 consoleAppender ,要拿到 com.c 的 parentCategory (com) ,
     而 rootLogger 拿到了 Category(com) 需要 拿到 consoleAppender 导致 死锁  
     4.  public
          Logger makeNewLoggerInstance(String name) {
            return new Logger(name);
          }    
          Logger 不是 单例 模式 , 是 根据 classpath new 出来 的 
    
    
    
"RootLoggerThread":
        at org.apache.log4j.AppenderSkeleton.doAppend(AppenderSkeleton.java:231)
        - waiting to lock <0x000000076c242690> (a org.apache.log4j.ConsoleAppender)
        at org.apache.log4j.helpers.AppenderAttachableImpl.appendLoopOnAppenders(AppenderAttachableImpl.java:66)
        at org.apache.log4j.Category.callAppenders(Category.java:206)
        - locked <0x000000076bf0fd98> (a org.apache.log4j.spi.RootLogger)
        at org.apache.log4j.Category.forcedLog(Category.java:391)
        at org.apache.log4j.Category.info(Category.java:666)
        at com.main.RootLoggerThread.run(RootLoggerThread.java:14)
"AnObjectThread":
        at org.apache.log4j.Category.callAppenders(Category.java:205)
        - waiting to lock <0x000000076bf0fd98> (a org.apache.log4j.spi.RootLogger)
        at org.apache.log4j.Category.forcedLog(Category.java:391)
        at org.apache.log4j.Category.info(Category.java:666)
        at com.a.AnObject.toString(AnObject.java:20)
        at org.apache.log4j.or.DefaultRenderer.doRender(DefaultRenderer.java:37)
        at org.apache.log4j.or.RendererMap.findAndRender(RendererMap.java:80)
        at org.apache.log4j.spi.LoggingEvent.getRenderedMessage(LoggingEvent.java:368)
        at org.apache.log4j.helpers.PatternParser$BasicPatternConverter.convert(PatternParser.java:402)
        at org.apache.log4j.helpers.PatternConverter.format(PatternConverter.java:65)
        at org.apache.log4j.PatternLayout.format(PatternLayout.java:506)
        at org.apache.log4j.WriterAppender.subAppend(WriterAppender.java:310)
        at org.apache.log4j.WriterAppender.append(WriterAppender.java:162)
        at org.apache.log4j.AppenderSkeleton.doAppend(AppenderSkeleton.java:251)
        - locked <0x000000076c242690> (a org.apache.log4j.ConsoleAppender)
        at org.apache.log4j.helpers.AppenderAttachableImpl.appendLoopOnAppenders(AppenderAttachableImpl.java:66)
        at org.apache.log4j.Category.callAppenders(Category.java:206)
        - locked <0x000000076c441668> (a org.apache.log4j.Logger)
        at org.apache.log4j.Category.forcedLog(Category.java:391)
        at org.apache.log4j.Category.info(Category.java:666)
        at com.c.AnObjectThread.run(AnObjectThread.java:16)
  