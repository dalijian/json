package com.lijian.Class;

import org.junit.Test;

import java.util.List;

public class ClassTest {



    @Test
    public void testClass(){
        System.out.println(new IFaceImpl() instanceof IFace); // true
        System.out.println(new IFaceImplExt() instanceof IFace); // true


        try {
            // 用于 判断 是否 是 指定 类型 的 子类
            IFace object = Class.forName("com.lijian.Class.IFaceImplExt").asSubclass(IFace.class).newInstance();
            System.out.println(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//        父-->子
    @Test
    public void isAssignableFromTest(){

     boolean flag =  IFace.class.isAssignableFrom(IFaceImplExt.class);
        System.out.println("flag:" + flag);
     boolean flag2 = IFaceImpl.class.isAssignableFrom(IFaceImplExt.class);
        System.out.println("flag2:"+flag2);


        boolean flag3 =   IFace.class.isAssignableFrom(IFaceImpl.class);
        System.out.println("flag3:" + flag3);
        boolean flag4= new IFaceImpl().getClass().isAssignableFrom(IFaceImpl.class);
        System.out.println("flag4:"+flag4);
    }
}
interface IFace {

}

class IFaceImpl implements IFace {

}

class IFaceImplExt extends IFaceImpl {

}
