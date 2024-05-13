/*
 * Copyright 2015 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appform.ranger.http.server;

import io.appform.ranger.http.server.bundle.HttpServerBundle;
import io.appform.ranger.http.server.bundle.config.RangerHttpConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<HttpAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<HttpAppConfiguration> bootstrap) {
        bootstrap.addBundle(new HttpServerBundle<HttpAppConfiguration>() {
            @Override
            protected RangerHttpConfiguration getRangerConfiguration(HttpAppConfiguration configuration) {
                return configuration.getRangerConfiguration();
            }
        });
    }

    @Override
    public void run(HttpAppConfiguration appConfiguration, Environment environment) {
        //Nothing to do in run!
    }
}
