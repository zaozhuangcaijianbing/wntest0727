package com.wn.wangzheng.adaptor.demo1;

// 注意：适配器类的命名不一定非得末尾带Adaptor
public class CDAdaptor extends CD implements ITarget {
    //...
    public void function1() {
        super.staticFunction1();
    }

    public void function2() {
        super.uglyNamingFucntion2();
    }

    public void function3(ParamsWrapperDefinition paramsWrapper) {
        super.tooManyParamsFunction3(paramsWrapper.getParamA(), 0);
    }

    public void function4() {
        //...reimplement it...
    }
}
