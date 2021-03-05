java 工具包 demo
json 相关包解析 demo

1. webservice -restful java-rsf




Web 资源可以有不同的表现形式，服务端与客户端之间需要一种称为内容协商
    1. Resource 方法的 Produces 标注用于指定响应体的数据格式（MIME 类型），
    2. Consumes 标注用于指定请求体的数据格式；作为客户端，
    3. Accept 请求头用于选择响应体的数据格式，
    4. Content-Type 请求头用于标识请求体的数据格式。