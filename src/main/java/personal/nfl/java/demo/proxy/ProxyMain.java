package personal.nfl.java.demo.proxy;

import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyMain {

    static {

    }

    public static void main(String[] args) {

        // 注意这里的 true 必须为 字符串才有效 ，执行后会在项目目录下生成 com.sun.proxy 包，代理的过程类就在里面
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();
        Subject2 realSubject2 = new RealSubject2();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);
        InvocationHandler handler2 = new DynamicProxy2(realSubject2);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);
        Subject2 subject2 = (Subject2) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject2
                .getClass().getInterfaces(), handler2);

        System.out.println("$Proxy0.class全名: "+Proxy.getProxyClass(subject2.getClass().getClassLoader(), Subject.class));
//        System.out.println("$Proxy0.class全名: "+Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class));
        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}
