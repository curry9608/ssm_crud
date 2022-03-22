package org.dh.ssm.crud.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加 Msg 类引入链式编程  服务器执行完删除或者添加之后，状态信息应该返回给浏览器，Msg可以实现一个通用的返回
 * @author donghan
 * @create 2022-03-21 9:51
 */
@Data
public class Msg {
    private int code;//状态码 100成功 200失败
    private String msg;//用户返回给浏览器的数据
    private Map<String, Object> data = new HashMap<>();//用户要返回给浏览器的数据

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    //链式编程
    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
