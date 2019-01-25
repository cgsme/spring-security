package org.tutu.springsecurity.browser.support;

/**
 * @author cguisheng
 * @className: SimpleResponse.java
 * @description: TODO
 * @date 2019/1/20 13:34
 */
public class SimpleResponse {

    private Object object;

    public SimpleResponse(Object content) {
        this.object = content;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
