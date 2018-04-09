/*
 * Copyright 2018 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onehippo.forge.servlet.decorators.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public abstract class CommonUndecorateFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(CommonUndecorateFilter.class);

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HippoDecoratedServletRequest) {
            log.debug("Un-decorating request");
            final HippoDecoratedServletRequest decoratedServletRequest = (HippoDecoratedServletRequest) request;
            final ServletRequest originalRequest = decoratedServletRequest.getRequest();
            chain.doFilter(originalRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        
    }


    @Override
    public void destroy() {

    }


}
