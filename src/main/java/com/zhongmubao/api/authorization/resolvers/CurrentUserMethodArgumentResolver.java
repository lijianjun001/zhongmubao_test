package com.zhongmubao.api.authorization.resolvers;

import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.CustomerDao;
import com.zhongmubao.api.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @author 孙阿龙
 * @see CurrentUser
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是Customer并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(Customer.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        Object currentUserId =  webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if(currentUserId==null) {
            return null;
        }
        return customerDao.getCustomerById((int)currentUserId);
        //throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
