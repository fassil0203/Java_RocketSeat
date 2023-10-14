package br.com.fabiosantana.todolist.utils;

import org.springframework.beans.*;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;


public class utils {

    public static void  copyNonNullProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));      // consegue copiar as propriedades de um objeto p/ outro objeto.

    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());      // Vai guardar todas as propriedades nulas.
            }

        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);      //Converte o conjunto de nome das propriedades p array string

    }
}
