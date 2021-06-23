package com.bridge.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * filterType : 사용하는 필터타입을 명시한다. 필터 적용 시점에 따라 pre, post로 나뉨
 * filterOrder : 필터의 갯수. 지금은 한개만 설정하기 때문에 1로 설정
 * shouldFilter : 지금 필터를 사용할 것인가를 설정
 *
 * @Slf4j : lombok 라이브러리를 사용한 log 구현. 코드로 구현하면
 *          Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
 *          이렇게 작성 가능하다.
 */

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * HttpServletRequest 정보를 최상위에 있는 RequestContext의 getCurrentContext로부터 얻는다.
     * run()는 사용자의 요청이 들어올때마다 가장 먼저 실행되는 메서드다.
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("************* printing logs : ");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("************* " + request.getRequestURI());
        return null;
    }
}
