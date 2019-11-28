1. 发布者订阅者 模式 ，可以 实现 发布者 消息 更新时， 可以 主动 推向 订阅者 ，功能上类似 webSocket
2. * <p>The type of event will be indicated by the method's first (and only) parameter. If this
    * annotation is applied to methods with zero parameters, or more than one parameter, the object
    * containing the method will not be able to register for event delivery from the {@link EventBus}.
   @Subscribe 不支持 设置 订阅 消息类型，所以 用来 区分订阅 消息类型，可以 通过 @Subscribe 注解的方法接受参数 类型 
3. 监听事件类型 Number , Number 是 Integer,Long  等的 父类， 所以 Integer,Long  等 事件  更新 时， Number  也能 接受到,所以 可以 使用订阅 父类类型 ，从而实现 全局的 事件 订阅   