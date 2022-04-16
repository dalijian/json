1. @JsonValue  一个 实体类 只能 使用 一个属性 序列化 输出 该对象值
2. @JsonUnwrapped 序列化 对象扁平化   
3. @JsonAnySetter 可以实现 反序列化 构造对象 ， 但是 对象不能为空 
4. @JsonFilter  可以 过滤 指定 属性集合 
5. @JsonView  过滤  