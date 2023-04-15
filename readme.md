java 工具包 demo
json 相关包解析 demo

1. webservice -restful java-rsf




Web 资源可以有不同的表现形式，服务端与客户端之间需要一种称为内容协商
    1. Resource 方法的 Produces 标注用于指定响应体的数据格式（MIME 类型），
    2. Consumes 标注用于指定请求体的数据格式；作为客户端，
    3. Accept 请求头用于选择响应体的数据格式，
    4. Content-Type 请求头用于标识请求体的数据格式。
    
### jaxb 实现 xml to java bean ,java bean to xml 



----------
2022/4/3 21:10:12 
#### crc32 checksum 校验 文件 类似 文件 的 hashcode 


----------
2022/4/9 23:00:58 
###NIO
1. selector.select() 只会 阻塞 
2. selector.select() 返回 int 值 为 register  SelectionKey 数量 。与 http 请求数 无关
3. 处理 SelectionKey.OP_READ 不能 关闭 channel 否则 就 不能 往 channel 写入 bufferByte ,SelectionKey.OP_WRITE 需要 关闭 channel  否则  selector.select() 不为0 不会 阻塞 
4. SelectionKey 是channel 的 映射 ，存放 的 就是 channel




----------
2022/4/16 23:08:25 
###proxy
1. proxy 代理类的 构造方法没法调用 , 代理类 只是起到 装饰 作用 
2. proxy 代理类 需要 注入到 new 对象中 ，这样 new 对象 调用 代理类方法时 ，才会处方 代理 接口 方法 覆写  