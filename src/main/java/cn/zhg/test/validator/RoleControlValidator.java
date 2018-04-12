/**
 * 创建于2018-04-11 21:52:44
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.*;
import javax.validation.constraintvalidation.*;

import xiaogen.util.Logger;

/**
 * 多个参数校验,跨参数校验
 * 
 * @author zhhaogen
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class RoleControlValidator implements ConstraintValidator<RoleControl, Object[]>
{
	private RoleControl an;

	@Override
	public void initialize(RoleControl constraintAnnotation)
	{
		this.an = constraintAnnotation;
	}

	@Override
	public boolean isValid(Object[] values, ConstraintValidatorContext context)
	{
//		Logger.d("values=" + Arrays.toString(values));
		if (values.length < 2)
		{
			throw new IllegalArgumentException("错误方法参数");
		}
		String root = an.value();// 管理员组
		if (root.equals(values[0]))
		{
			return true;
		}
		if (Objects.equals(values[0], values[1]))
		{
			return true;
		} 
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("非"+root+"不能访问").addConstraintViolation();
		return false;
	}

}
