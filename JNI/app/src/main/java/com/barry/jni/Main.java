package com.barry.jni;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by bynn on 2018/2/12.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class clazz = Main.class.getClassLoader().loadClass("com.barry.jni.Dialog");
            Method method = clazz.getMethod("show", String.class);
            method.invoke(clazz.newInstance(), "恭喜熊大和光頭鵬達成成就：愛情的結晶");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
