//XWPFParagraph 段落属性
//paragraphX.addRun(runX0);//似乎并没有什么卵用
//paragraphX.removeRun(1);//按数组下标删除run(文本)
paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
//paragraphX.setBorderBetween(Borders.LIGHTNING_1);//边界 （但是我设置了好几个值都没有效果）
//paragraphX.setFirstLineIndent(100);//首行缩进：-----效果不详
//paragraphX.setFontAlignment(3);//字体对齐方式：1左对齐 2居中3右对齐
//paragraphX.setIndentationFirstLine(2);//首行缩进：-----效果不详
//paragraphX.setIndentationHanging(1);//指定缩进，从父段落的第一行删除，将第一行上的缩进移回到文本流方向的开头。-----效果不详
//paragraphX.setIndentationLeft(2);//-----效果不详
//paragraphX.setIndentationRight(2);//-----效果不详
//paragraphX.setIndentFromLeft(2);//-----效果不详
//paragraphX.setIndentFromRight(2);//-----效果不详
//paragraphX.setNumID(new BigInteger("3"));//设置段落编号-----有效果看不懂（仅仅是整段缩进4个字）
//paragraphX.setPageBreak(true);//段前分页
//paragraphX.setSpacingAfter(1);//指定文档中此段最后一行以绝对单位添加的间距。-----效果不详
//paragraphX.setSpacingBeforeLines(2);//指定在该行的第一行中添加行单位之前的间距-----效果不详
//paragraphX.setStyle("标题 3");//段落样式：需要结合addCustomHeadingStyle(docxDocument, "标题 3", 3)配合使用
paragraphX.setVerticalAlignment(TextAlignment.BOTTOM);//文本对齐方式(我猜在table里面会有比较明显得到效果)
paragraphX.setWordWrapped(true);//这个元素指定一个消费者是否应该突破拉丁语文本超过一行的文本范围，打破单词跨两行（打破字符水平）或移动到以下行字（打破字级）-----(我没看懂:填个false还报异常了)
##RUN
//默认：宋体（wps）/等线（office2016） 5号 两端对齐 单倍间距
runX.setText("舜发于畎亩之中， 傅说举于版筑之间， 胶鬲举于鱼盐之中， 管夷吾举于士...");
runX.setBold(false);//加粗
runX.setCapitalized(false);//我也不知道这个属性做啥的
//runX.setCharacterSpacing(5);//这个属性报错
runX.setColor("BED4F1");//设置颜色--十六进制
runX.setDoubleStrikethrough(false);//双删除线
runX.setEmbossed(false);//浮雕字体----效果和印记（悬浮阴影）类似
//runX.setFontFamily("宋体");//字体
runX.setFontFamily("华文新魏", FontCharRange.cs);//字体，范围----效果不详
runX.setFontSize(14);//字体大小
runX.setImprinted(false);//印迹（悬浮阴影）---效果和浮雕类似
runX.setItalic(false);//斜体（字体倾斜）
//runX.setKerning(1);//字距调整----这个好像没有效果
runX.setShadow(true);//阴影---稍微有点效果（阴影不明显）
//runX.setSmallCaps(true);//小型股------效果不清楚
//runX.setStrike(true);//单删除线（废弃）
runX.setStrikeThrough(false);//单删除线（新的替换Strike）
//runX.setSubscript(VerticalAlign.SUBSCRIPT);//下标(吧当前这个run变成下标)---枚举
//runX.setTextPosition(20);//设置两行之间的行间距//runX.setUnderline(UnderlinePatterns.DASH_LONG);//各种类型的下划线（枚举）
//runX0.addBreak();//类似换行的操作（html的  br标签）
runX0.addTab();//tab键
runX0.addCarriageReturn();//回车键

注意：addTab()和addCarriageReturn() 对setText()的使用先后顺序有关：比如先执行addTab,再写Text这是对当前这个Text的Table，反之是对下一个run的Text的Tab效果