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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HippoDecoratedServletRequest extends HttpServletRequestWrapper {

    private static final Logger log = LoggerFactory.getLogger(HippoDecoratedServletRequest.class);
    private final DecoratorConfiguration config;

    public HippoDecoratedServletRequest(HttpServletRequest request, final DecoratorConfiguration config) {
        super(request);
        this.config = config;
    }

    @Override
    public String getContextPath() {
        if (config.disabled() || config.invalid()) {
            return super.getContextPath();
        }
        final String contextPath = config.getContextPath();
        log.debug("Using decorated context path: {}", contextPath);
        return contextPath;
    }
}
