1. selector.select() 只会 阻塞 
2. selector.select() 返回 int 值 为 register  SelectionKey 数量 。与 http 请求数 无关
3. 处理 SelectionKey.OP_READ,SelectionKey.OP_WRITE 需要 关闭 channel  否则  selector.select() 不为0 不会 阻塞  

