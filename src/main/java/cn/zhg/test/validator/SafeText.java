/**
 * 创建于2018-04-11 20:03:11
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.lang.annotation.*;  
import javax.validation.*; 
import javax.validation.constraints.*;

/**
 * 校验文明用语
 * <p>
 * 自定义注解
 * 
 * @author zhhaogen
 *
 */ 
@Constraint(validatedBy = {SafeTextValidator.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER,ElementType.FIELD }) 
public @interface SafeText
{
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	String message() default "内容不符合规范";
	/**
	 * 黑名单,单词
	 * @return
	 */
	String[] value() default {"最高","最好"};
}
