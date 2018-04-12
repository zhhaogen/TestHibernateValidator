/**
 * 创建于2018-04-11 20:30:48
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 注解SafeText校验实现,仅支持CharSequence类
 * @author zhhaogen
 *
 */
public class SafeTextValidator implements ConstraintValidator<SafeText,CharSequence>
{  
	private SafeText an; 
	@Override
	public void initialize(SafeText constraintAnnotation)
	{
		this.an=constraintAnnotation;
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context)
	{
		if(value==null)
		{
			return true;
		}
		String txt=value.toString();
		for(String v:an.value())
		{
			 if(txt.contains(v))
			 {
				 return false;
			 }
		}
		return true;
	}

}
