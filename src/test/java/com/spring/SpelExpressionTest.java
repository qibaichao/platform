package com.spring;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Created by qibaichao on 2018/5/7.
 */
public class SpelExpressionTest  {

    public static void main(String[] args) {
        ExpressionParser ep=new SpelExpressionParser();
        System.out.println(ep.parseExpression("new java.util.Date()").getValue());
    }
}
