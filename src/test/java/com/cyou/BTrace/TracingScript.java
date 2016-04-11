package com.cyou.BTrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * Created by qibaichao on 2016/4/11.
 * 官网https://kenai.com/
 * demo:http://www.tuicool.com/articles/jIJrEf
 * 启动命令
 * btrace 14416 TracingScript.java
 */
@BTrace
public class TracingScript {

    @TLS
    private static long startTime = 0;

    @OnMethod(
            clazz = "com.cyou.BTrace.CaseObject",
            method = "execute"
    )
    public static void startExecute() {
        startTime = timeNanos();
    }

    @OnMethod(
            clazz = "com.cyou.BTrace.CaseObject",
            method = "execute",
            location = @Location(Kind.RETURN)
    )
    public static void endExecute(@Duration long duration) {
        long time = timeNanos() - startTime;
        println(strcat("execute time(nanos): ", str(time)));
        println(strcat("duration(nanos): ", str(duration)));
    }
}
