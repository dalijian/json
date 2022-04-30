1. listener 监听器 有 enter 和 exit 两个 方法 
2. visitor  只有 visit 一个方法 
3. visitor 有 返回值， listener 没有 返回值 ，
    1.1 visitor 子树 需要 手动 调用 ，listener 则 不需要手动 ， antlr 会 自动 调用 
3. import 导入 其他 .g4 文件
#  给 备选 分支 添加 标签 用于 生成 方法

5. TokenStreamRewriter 支持 修改 TokenStream , 这样 就可以 在 原有 基础上 添加 新 内容 
