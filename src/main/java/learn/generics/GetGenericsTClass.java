package learn.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import junit.framework.TestCase;

/**
 * 若要过去T.class的正确实际类型。则使用了泛型的类需要继承该类(该类也可以为接口形式)
 * 
 * @author lenovopc
 *
 * @param <T>
 */
abstract class AbstractGenericsTClass<T> {
	
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public AbstractGenericsTClass() {
		clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getObjectType() {
		return clazz;
	}
	
	public T getObject() {
		try {
			T target = clazz.newInstance();
			return target;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

/**
 * 获取泛型T.class的正确写法 
 * 这里能通过反射机制获取实际类型InterfaceBean
 * 注意区别：AbstractGenericsTClass<>泛型里必须指定实际的类型而非泛型T
 * 
 * @author lenovopc
 *
 * @param <T>
 */
class GoodInterfaceBeanFactory<T> extends AbstractGenericsTClass<InterfaceBean> {
	public ParameterizedType getClassT() {
		return (ParameterizedType) getClass().getGenericSuperclass();
	}
}

/**
 * 获取泛型T.class的错误用法 
 * 此处获取的T.class的类型为[T]而非需要的实际类型InterfaceBean
 * 注意区别：AbstractGenericsTClass<>泛型里必须指定实际的类型而非泛型T
 * 
 * @author lenovopc
 *
 * @param <T>
 */
class BadInterfaceBeanFactory<T> extends AbstractGenericsTClass<T> {
	public ParameterizedType getClassT() {
		return (ParameterizedType) getClass().getGenericSuperclass();
	}
}

/**
 * 泛型类型代表的实际类型, 可以为任意类型
 * @author lenovopc
 *
 */
interface InterfaceBean {
}

public class GetGenericsTClass extends TestCase {

	private void print(Type[] targs) {
		System.out.print("actual type arguments are:");
		for (int j = 0; j < targs.length; j++) {
			System.out.print(" instance of " + targs[j].getClass().getName() + ":");
			System.out.println(" (" + targs[j] + ")");
		}
	}

	public void testGoodClass() throws Exception {
		GoodInterfaceBeanFactory<InterfaceBean> factory = new GoodInterfaceBeanFactory<InterfaceBean>();
		ParameterizedType type = factory.getClassT();
		Type[] types = type.getActualTypeArguments();
		
		// 控制台输出:actual type arguments are: instance of java.lang.Class: (interface learn.generics.InterfaceBean)
		print(types);

		assertEquals(AbstractGenericsTClass.class, type.getRawType());
		assertEquals(InterfaceBean.class, types[0]);
	}

	public void testBadClass() throws Exception {
		ParameterizedType type = new BadInterfaceBeanFactory<InterfaceBean>().getClassT();
		Type[] types = type.getActualTypeArguments();
		
		//控制台输出：actual type arguments are: instance of sun.reflect.generics.reflectiveObjects.TypeVariableImpl: (T)
		print(types);

		assertEquals(AbstractGenericsTClass.class, type.getRawType());
		assertEquals(InterfaceBean.class, types[0]);
	}
}