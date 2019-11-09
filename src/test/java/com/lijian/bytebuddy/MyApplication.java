package com.lijian.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

class MyApplication {
  public static void main(String[] args) {
//    TypePool typePool = TypePool.Default.ofClassPath();
//    new ByteBuddy()
//      .redefine(typePool.describe("foo.Bar").resolve(), // do not use 'Bar.class'
//                ClassFileLocator.ForClassLoader.ofClassPath())
//      .defineField("qux", String.class) // we learn more about defining fields later
//      .make()
//      .load(ClassLoader.getSystemClassLoader());
//    assertThat(Bar.class.getDeclaredField("qux"), notNullValue());
  }
}