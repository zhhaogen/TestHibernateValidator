/**
 * 创建于2018-04-11 19:29:42
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.validation.*;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.*;

/**
 * @author zhhaogen
 *
 */
public class MainTest
{
	private Validator validator;

	@Before
	public void getValidator()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// ValidatorFactory factory =
		// Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * 断言校验测试
	 * 
	 * @param item
	 *            测试item
	 * @param b
	 *            是否通过校验
	 */
	private <T> void assertTestBean(T item, boolean b)
	{
		Set<ConstraintViolation<T>> result = validator.validate(item);
		result.forEach(res -> {
			System.out.println("[" + res.getPropertyPath() + "] " + res.getMessage());
		});
		assertEquals(b, result.isEmpty());
	}

	/**
	 * 断言校验方法测试
	 * 
	 * @param b
	 *            是否通过校验
	 * @param clz
	 *            测试类
	 * @param methodName
	 *            测试方法名
	 * @param parameterValues
	 *            方法传入参数
	 */
	private <T> void assertTestMethod(boolean b, Class<T> clz, String methodName, Object... parameterValues)
	{
		Object object = null;
		Method method = null;
		try
		{
			object = clz.newInstance();
			method = Arrays.asList(clz.getMethods()).stream().filter(m -> m.getName().equals(methodName)).findFirst()
					.get();
		} catch (Exception igr)
		{

		}
		Set<ConstraintViolation<Object>> result = validator.forExecutables().validateParameters(object, method,
				parameterValues);
		result.forEach(res -> {
			System.out.println("[" + res.getPropertyPath() + "] " + res.getMessage());
		});
		assertEquals(b, result.isEmpty());
	}

	/**
	 * 断言校验方法测试
	 * 
	 * @param b
	 *            是否通过校验
	 * @param clz
	 *            测试类
	 * @param methodName
	 *            测试方法名
	 * @param parameterValues
	 *            方法传入参数
	 */
	private <T> void assertTestMethodReturnValue(boolean b, Class<T> clz, String methodName, Object... parameterValues)
	{
		Object object = null;
		Method method = null;
		Object returnValue = null;
		try
		{
			object = clz.newInstance();
			method = Arrays.asList(clz.getMethods()).stream().filter(m -> m.getName().equals(methodName)).findFirst()
					.get();
			returnValue = method.invoke(object, parameterValues);
		} catch (Exception igr)
		{

		}
		Set<ConstraintViolation<Object>> result = validator.forExecutables().validateReturnValue(object, method,
				returnValue);
		;
		result.forEach(res -> {
			System.out.println("[" + res.getPropertyPath() + "] " + res.getMessage());
		});
		assertEquals(b, result.isEmpty());
	}

	/**
	 * 简单测试,异常测试1
	 */
	@Test
	public void testSimpleBean1()
	{
		User item = new User();
		item.brithDay = new Date();
		assertTestBean(item, false);
	}

	/**
	 * 简单测试,正常返回
	 */
	@Test
	public void testSimpleBean2()
	{
		User item = new User();
		item.brithDay = new Date();
		item.name = "小明";
		item.level = 1;
		assertTestBean(item, true);
	}

	/**
	 * 自定义组合注解,测试
	 */
	@Test
	public void testCombineBean1()
	{
		Order item = new Order();
		item.address = "广州市";
		item.tel = "10086";
		assertTestBean(item, false);
	}

	/**
	 * 自定义组合注解,测试
	 */
	@Test
	public void testCombineBean2()
	{
		Order item = new Order();
		item.address = "广州市";
		item.tel = "13800138000";
		assertTestBean(item, true);
	}

	/**
	 * 自定义注解,测试
	 */
	@Test
	public void testMyBean1()
	{
		Comment item = new Comment();
		item.date = new Date();
		item.txt = "一切正常";
		assertTestBean(item, true);
	}

	/**
	 * 自定义注解,测试
	 */
	@Test
	public void testMyBean2()
	{
		Comment item = new Comment();
		item.date = new Date();
		item.txt = "一切正常,没人是最好的";
		assertTestBean(item, false);
	}

	/**
	 * 方法执行测试，校验传入参数
	 */
	@Test
	public void testMyMethod1()
	{
		assertTestMethod(false, TestServer.class, "addUser", null, 2);
		assertTestMethod(false, TestServer.class, "addUser", "", 2);
		assertTestMethod(true, TestServer.class, "addUser", "西门吹雪", 2);
		assertTestMethod(false, TestServer.class, "addOrder", null, "10086'");
		assertTestMethod(true, TestServer.class, "addOrder", "广州市", "13800138000");
		assertTestMethod(true, TestServer.class, "addComment", "zzz", "一切支持'");
		assertTestMethod(false, TestServer.class, "addComment", "zzz", "大家好才是最好的'");
	}
	/**
	 * 方法执行测试，校验返回值
	 */
	@Test
	public void testMyMethod2()
	{
		assertTestMethodReturnValue(true, TestServer.class, "saveComment", "zzz", "大家好'");
		assertTestMethodReturnValue(false, TestServer.class, "saveComment", "zzz", "大家好才是最好的'");
		assertTestMethodReturnValue(false, TestServer.class, "saveCommentNull", "zzz", "大家好才是最好的'");

	}
	/**
	 * 方法执行测试，校验方法多个参数
	 */
	@Test
	public void testMyMethod3()
	{
		assertTestMethod(true, TestServer.class, "deleteUser", "zzz", "zzz");
		assertTestMethod(false, TestServer.class, "deleteUser", "zzz", "aaa");
		assertTestMethod(true, TestServer.class, "deleteUser", "root", "aaa");
		assertTestMethod(true, TestServer.class, "deleteUser", "root", "");
		assertTestMethod(false, TestServer.class, "deleteNotEmptyUser", "root", "");
		assertTestMethod(true, TestServer.class, "deleteNotEmptyUser", "root", "abc");
	}
}
