package com.tigrex.user;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk的动态代理，GClib的继承型的动态代理就不写了，spring会在两种方式间切换，jdk1.8之后
 * jdk的动态代理比GClib快
 */
public class ProxyTest {

    public interface IHello{
        void sayHello();
    }

    public class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello user");
        }
    }

    public interface IUser{
        void sayUser();
    }

    public class User implements IUser{
        @Override
        public void sayUser() {
            System.out.println("user create");
        }
    }

    /**
     * 代理工厂
     */
    public class ProxyFactory implements InvocationHandler{

        Object target;

        public ProxyFactory(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 执行相应的目标方法
            Object template = method.invoke(target, args);
            return template;
        }
    }


    @Test
    public void main() {

        IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[]{IHello.class}, new ProxyFactory(new Hello()));
        hello.sayHello();

        IUser user = (IUser)Proxy.newProxyInstance(IUser.class.getClassLoader(), new Class[]{IUser.class}, new ProxyFactory(new User()));
        user.sayUser();

    }

}
