代理模式
介绍
意图：为其他对象提供一种代理以控制或扩展对这个对象的操作。想在访问一个类时做一些其他的操作

主要解决：
在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。
在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），直接访问会给使用者或者系统结构带来很多麻烦，
我们可以在访问此对象时加上一个对此对象的访问层。

何时使用：想在访问一个类时做一些控制。
如何解决：增加中间层。
关键代码：实现与被代理类组合。

应用实例： 
	1、Windows 里面的快捷方式。 
	2、猪八戒去找高翠兰结果是孙悟空变的，可以这样理解：把高翠兰的外貌抽象出来，高翠兰本人和孙悟空都实现了这个接口，猪八戒访问高翠兰的时候看不出来这个是孙悟空，所以说孙悟空是高翠兰代理类。 
	3、买火车票不一定在火车站买，也可以去代售点。 
	4、一张支票或银行存单是账户中资金的代理。支票在市场交易中用来代替现金，并提供对签发人账号上资金的控制。 
	5、spring aop。

代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
这里使用到编程中的一个思想:不要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法

举个例子1：
假如说我现在想买一辆二手车，虽然我可以自己去找车源，做质量检测等一系列的车辆过户流程，但是这确实太浪费我得时间和精力了。
我只是想买一辆车而已为什么我还要额外做这么多事呢？于是我就通过中介公司来买车，他们来给我找车源，帮我办理车辆过户流程，我只是负责选择自己喜欢的车，然后付钱就可以了。
举个例子2:
假设我们想邀请一位明星,那么并不是直接连接明星,而是联系明星的经纪人,来达到同样的目的.明星就是一个目标对象,他只要负责活动中的节目,而其他琐碎的事情就交给他的代理人(经纪人)来解决.

1.1.静态代理
静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
静态代理总结:
	1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
	2.缺点:因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
		
如何解决静态代理中的缺点呢?答案是可以使用动态代理方式

1.2.动态代理
动态代理有以下特点:
1.代理对象,不需要实现接口
2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
3.动态代理也叫做:JDK代理,接口代理

1.2.1 JDK中生成代理对象的API
代理类所在包:java.lang.reflect.Proxy
JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是:

static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
代码示例:
接口类IUserDao.java以及接口实现类,目标对象UserDao是一样的,没有做修改.在这个基础上,增加一个代理工厂类(ProxyFactory.java),将代理类写在这个地方,然后在测试类(需要使用到代理的代码)中先建立目标对象和代理对象的联系,然后代用代理对象的中同名方法

代理工厂类:ProxyFactory.java

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory{

    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }

   //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                }
        );
    }

}
测试类:App.java
/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        // 目标对象
        IUserDao target = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}

总结:
代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理


1.3 Cglib代理
上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,这个时候就可以使用以目标对象子类的方式类实现代理,
这种方法就叫做:Cglib代理

Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.

JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用Cglib实现.
Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.它广泛的被许多AOP的框架使用,例如Spring AOP和synaop,为他们提供方法的interception(拦截)
Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类.不鼓励直接使用ASM,因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉.
Cglib子类代理实现方法:
1.需要引入cglib的jar文件,但是Spring的核心包中已经包括了Cglib功能,所以直接引入pring-core-3.2.5.jar即可.
2.引入功能包后,就可以在内存中动态构建子类
3.代理的类不能为final,否则报错
4.目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.

代码示例:
目标对象类:UserDao.java

/**
 * 目标对象,没有实现任何接口
 */
public class UserDao {

    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
Cglib代理工厂:ProxyFactory.java

/**
 * Cglib子类代理工厂
 * 对UserDao在内存中动态构建一个子类对象
 */
public class ProxyFactory implements MethodInterceptor{
    //维护目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");

        //执行目标对象的方法
        //特别注意,invoke方法第一个参数是target而不是intercept(...)方法的obj,其他正确写法请参考CglibProxyFactory
        Object returnValue = method.invoke(target, args);
        
        // 下面三种写法是正确的(returnValue,returnValue1,returnValue2)
		// (returnValueError1,returnValueError2,returnValueError3均是错误写法，会出现死循环)
		// 区别：注意方法里面的obj和target。
		// 使用invoke方法第一个参数必须是代理类ProxyFactory的上下文target, 而使用invokeSuper方法第一个参数则必须为intercept方法的第一个参数(obj)。
		// Object returnValue = method.invoke(target, args);
		// Object returnValue1 = methodProxy.invoke(target, args);
		// Object returnValue2 = methodProxy.invokeSuper(obj, args);
		
		//下面三种是错误写法，会出现死循环，巨坑巨坑巨坑。。。。。。。
		// Object returnValueError1 = method.invoke(obj, args);
		// Object returnValueError2 = methodProxy.invoke(obj, args);
		// Object returnValueError3 = methodProxy.invokeSuper(target, args);

        System.out.println("提交事务...");

        return returnValue;
    }
}
测试类:

/**
 * 测试类
 */
public class App {

    @Test
    public void test(){
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}
在Spring的AOP编程中:
如果加入容器的目标对象有实现接口,用JDK代理
如果目标对象没有实现接口,用Cglib代理

cglib代理注意事项：
参考博客：https://blog.csdn.net/kingmax54212008/article/details/83902535
首先是要被代理的类，还是和常规的一样，声明自己的方法就行，但是要确保类和方法没有被final关键字修饰。
用final关键字修饰类会直接报异常，但是修饰方法不会抛异常，但是此方法不会被代理，但是不影响其他方法被代理。

这里非常坑的是invoke方法和invokeSuper的区别，
public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)() {
	
	//method.invoke(...);
	//methodProxy.invoke(...);
	//methodProxy.invokeSuper(...);
}

如果是用invoke方法一定要使用被代理的对象也就是上文中的target，而如果调用invokeSuper方法，则一定要使用被代理后的obj对象。


