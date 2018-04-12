/**
 * 创建于2018-04-11 19:27:52
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.util.Date;

import javax.validation.constraints.*;

/**
 * 测试模型,用户
 * @author zhhaogen
 *
 */
public class User
{
	@NotNull
	public String name;
	@Min(1)
	public int level; 
	public Date brithDay;
}
