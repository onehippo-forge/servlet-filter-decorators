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

package org.onehippo.forge.servlet.decorators.service;

import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.repository.modules.AbstractReconfigurableDaemonModule;
import org.onehippo.repository.modules.ProvidesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;

@ProvidesService(types = ServletDecoratorService.class)
public class ServletDecoratorModule extends AbstractReconfigurableDaemonModule {
    private static final Logger log = LoggerFactory.getLogger(ServletDecoratorModule.class);

    private ServletDecoratorService service;

    @Override
    protected void doConfigure(final Node node) {
        log.debug("(Re)configuring {}, with service {}", this.getClass().getSimpleName(),
                (service == null) ? "null" : service.getClass().getSimpleName());
        if (service != null) {
            service.setConfigurationChanged(true);
        }
    }

    @Override
    protected void doInitialize(final Session session) {
        service = new ServletDecoratorServiceImpl(session);
        HippoServiceRegistry.registerService(service, ServletDecoratorService.class);
    }

    @Override
    protected void doShutdown() {
        HippoServiceRegistry.unregisterService(service, ServletDecoratorService.class);
    }

}
