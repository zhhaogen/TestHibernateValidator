/**
 * 创建于2018-04-11 19:27:52
 * @author zhhaogen
 */
package cn.zhg.test.validator;

/**
 * 测试模型,订单
 * @author zhhaogen
 *
 */
public class Order
{ 
	public String address;  
	@PhoneNumber
	public String tel;  
}
