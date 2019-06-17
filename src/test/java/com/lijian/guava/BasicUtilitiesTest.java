package com.lijian.guava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;


import javax.annotation.Nullable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;

public class BasicUtilitiesTest {


    @Test
    public void optionOf() {

//        创建指定引用的Optional实例，若引用为null则快速失败
        Optional<String> result = Optional.of(null);

        if (result.isPresent()) {
            System.out.println(result.get());
        }
        Object result2 = Optional.of(null).orElse("lijain");

    }

    @Test
    public void check() {

//        检查boolean是否为true，用来检查传递给方法的参数  IllegalArgumentException
        checkArgument("lijian".length() > 4, "failure");

//        检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。
        checkNotNull("lijain".contains("li"));

//        用来检查对象的某些状态。
        checkState(true);

//        检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *
        checkElementIndex(4, 1);

//        检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *
        checkPositionIndex(1, 3);

//        检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*
        checkPositionIndexes(1, 2, 4);


//        *索引值常用来查找列表、字符串或数组中的元素，如List.get(int), String.charAt(int)

//*位置值和位置范围常用来截取列表、字符串或数组，如List.subList(int，int), String.substring(int)
    }


    //    使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException
    @Test
    public void Objects() {
        Objects.equal("a", "a");

        Objects.equal(null, "a");

        Objects.equal("a", null);

        Objects.equal(null, null);
    }

    @Test
    public void compare() {


    }

    @Test
    public void order() {

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
    }

//    @Test
//    public void throwable(){
//        try {
//            someMethodThatCouldThrowAnything();
//        } catch (IKnowWhatToDoWithThisException e) {
//            handle(e);
//        } catch (Throwable t) {
//            Throwables.propagateIfInstanceOf(t, IOException.class);
//            Throwables.propagateIfInstanceOf(t, SQLException.class);
//            throw Throwables.propagate(t);
//        }
//    }

//    @Test
//    public void tess(){
//
//        RuntimeException   propagate(Throwable);
//    }
}

class Foo {
    @Nullable
    String sortedBy;
    int notSortedBy;
}