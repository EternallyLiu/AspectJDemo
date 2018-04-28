package com.meitu.AspectJDemo;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Liu Pei  2018/4/24.
 * Mail ：Lp960822@outlook.com
 */

@Aspect
public class LogAspectJ {
    private long lastClickTime = 0;

    /**
     * 对传入方法的参数做判断，可用于权限判断
     *
     * @param x
     * @param str
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(public * *..testArgs(int,java.lang.String)) && args(x,str)")
    public void printArgsFun(int x, String str, ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("PrintArg", "这里是内部打印 x = " + x + " str = " + str);
        joinPoint.proceed();//执行被拦截的方法中的代码
    }

    /**
     * 防抖动处理
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(public * android.view.OnClickListener.onClick(..))")
    public void check(ProceedingJoinPoint joinPoint) throws Throwable {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime < 1000) {
            lastClickTime = currentClickTime;
            //1000以内多次点击则点击事件被舍弃不执行原来被拦截的方法的方法体
            return;
        }
        lastClickTime = currentClickTime;
        joinPoint.proceed();//执行被拦截的方法中的代码
    }
}
