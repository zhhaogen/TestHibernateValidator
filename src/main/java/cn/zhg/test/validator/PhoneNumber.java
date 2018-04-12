/**
 * 创建于2018-04-11 20:03:11
 * @author zhhaogen
 */
package cn.zhg.test.validator;

import java.lang.annotation.*;  
import javax.validation.*; 
import javax.validation.constraints.*;

/**
 * 校验手机号码
 * <p>
 * 组合注解@NotBlank,@Pattern 
 * 
 * @author zhhaogen
 *
 */
@NotBlank(message= "不能为空" )
@Pattern(regexp = "^[1][0-9]{10}$",message= "手机号码格式不对" ) 
@Constraint(validatedBy = { })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER,ElementType.FIELD })
//@ReportAsSingleViolation
public @interface PhoneNumber
{
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	String message() default "格式不对";
}
