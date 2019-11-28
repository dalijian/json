1. CompletionService 内部 维护了 一个 阻塞队列， 支持 批量提交异步任务，拿到返回值
    1. CompletionService将线程池Executor 和阻塞队列BlockingQueue的功能融合在一起
    2. CompletionService能够让异步任务的执行结果又序号，先执行完的先进入组塞队列
    3. 可以实现后续处理的有序性，避免无谓的等待，实现Forking Cluster 需求
1. 当需要批量提交异步任务测试  调用多个天气接口，拿到最先返回的数据，其他的取消
    1. 中华万年历：http://wthrcdn.etouch.cn/weather_mini?city=青岛
    2. 金山毒霸：http://weather.123.duba.net/static/weather_info/101120201.html?callback=
    3. 小米天气：http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101120201