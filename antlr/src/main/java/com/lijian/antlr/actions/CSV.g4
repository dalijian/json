grammar CSV;

/** 由 规则 file: hdr row+ 衍生 而来
  定义 局部变量 i， 使用 动作 $i++ 来 统计 当前 输入 的 行数 */

file
locals [int i=0]
    :hdr (rows+row[$hdr.text.split(",")]{$i++;})+
    {
    System.out.println($i+"rows");
    for(RowContext r:$rows){
        System.out.println("row token interval:"+r.getSourceInterval());
        }
    }
    ;

row[String[] columns] returns [Map<String,String> values]

locals [int col=0]
@init {
    $values= new HashMap<String,String>();
}
@after {
    if ($values!=null && $values.size()>0){
    System.out.println("values ="+$.values);
    }
}
: field
    {
    if($columns!=null){
        $values.put($columns[$col++].trim(),$field.text.trim());
        }
        }
        ( ',' field {
            if($columns!=null) {
                $values.put($columns[$col++].trim(),$field.text.trim());
                }
                }
                ) * '\r'? '\n'
                ;
