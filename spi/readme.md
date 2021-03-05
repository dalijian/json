###spi (Service Provider Interface)
1. 使用 配置 的方式 实现 接口 实现 ，达到 项目 解耦合  **基于接口的编程＋策略模式＋配置文件**
2. 与 class.forName() 反射实现 实例化 相比 ，不用 指明 具体 类 路径  ，但是 要 通过 文件 配置  
public final class ServiceLoader<S> implements Iterable<S>{
private static final String PREFIX = "META-INF/services/";

    // 代表被加载的类或者接口
    private final Class<S> service;

    // 用于定位，加载和实例化providers的类加载器
    private final ClassLoader loader;

    // 创建ServiceLoader时采用的访问控制上下文
    private final AccessControlContext acc;

    // 缓存providers，按实例化的顺序排列
    private LinkedHashMap<String,S> providers = new LinkedHashMap<>();

    // 懒查找迭代器
    private LazyIterator lookupIterator;
  
    ......
}