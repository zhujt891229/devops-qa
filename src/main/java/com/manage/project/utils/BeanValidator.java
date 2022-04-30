package com.manage.project.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.manage.project.exception.ParamException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {
    private static ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();

    public static void check(Object param) throws ParamException {
        if(param == null){
            throw new ParamException("传入的参数不能为空");
        }
        Map<String,String> result=validateObject(param);
        if(!CollectionUtils.isEmpty(result)){
            throw new ParamException(result.toString());
        }
    }

    private static Map<String,String> validateObject(Object first,Object... objects){
        if(objects != null&&objects.length>0){
            return validateList(Lists.asList(first,objects));
        }
        return validate(first,new Class[0]);
    }

    public static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object=iterator.next();
            errors = validate(object,new Class[0]);
        }while(errors.isEmpty());
        return errors;
    }

    public static <T> Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validateResult=validator.validate(t,groups);
        if(validateResult.isEmpty()){
            return Collections.EMPTY_MAP;
        }else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while(iterator.hasNext()){
                ConstraintViolation violation=(ConstraintViolation)iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }
}
