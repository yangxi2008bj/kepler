package com.lachesis.windranger.api.restful.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class RestApiVerRequestCondition implements RequestCondition<RestApiVerRequestCondition> {
	
	 // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    public RestApiVerRequestCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public RestApiVerRequestCondition combine(RestApiVerRequestCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new RestApiVerRequestCondition(other.getApiVersion());
    }

    @Override
    public int compareTo(RestApiVerRequestCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public RestApiVerRequestCondition getMatchingCondition(HttpServletRequest request) {
        //request.getPathInfo()
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getServletPath());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            if (version >= this.apiVersion) 
                // 如果请求的版本号大于等于配置版本号， 则满足
                return this;
        } else {
            // 如果版本号错误返回最新版本号
            return this;
        }
        return null;
    }
}
