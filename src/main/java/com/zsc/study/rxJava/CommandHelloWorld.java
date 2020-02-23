package com.zsc.study.rxJava;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Auther: zhangshanchuang
 * @Date: 20/2/12 17:20
 * @Description: hystrix 测试
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
    	//最少配置:指定命令组名(CommandGroup) 
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
    	// 依赖逻辑封装在run()方法中  
        // a real example would do work like a network call here
        return "Hello " + name + " !";
    }

    public static void main(String[] args) throws Exception{
    	//每个Command对象只能调用一次,不可以重复调用,  
        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.  
        //同步调用
    	CommandHelloWorld command = new CommandHelloWorld("Synchronous-hystrix");
    	//使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        String result= command.execute();
        System.out.println(result);
       
        //异步调用
        command = new CommandHelloWorld("Asynchronous-hystrix");
      //异步调用,可自由控制获取结果时机,  
        Future<String> future = command.queue();  
        //get操作不能超过command定义的超时时间,默认:1秒  
        result = future.get(100, TimeUnit.MILLISECONDS);  
        System.out.println(result);  
    }
}
