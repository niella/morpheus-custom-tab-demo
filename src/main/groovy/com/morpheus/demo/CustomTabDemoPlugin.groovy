/*
* Copyright 2022 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.morpheus.demo

import com.morpheusdata.core.Plugin
import com.morpheusdata.model.Permission
import com.morpheusdata.views.HandlebarsRenderer

class CustomTabDemoPlugin extends Plugin {

    @Override
    String getCode() {
        return 'customTabDemo'
    }

    @Override
    void initialize() {
        this.setName("CustomTabDemo")
        this.registerProvider(new CustomTabDemoInstanceTabProvider(this,this.morpheus))

        this.setName("Custom Tab Demo")
        this.setPermissions([Permission.build('Custom Tab Plugin','customTabPlugin', [Permission.AccessType.none, Permission.AccessType.full])])
        this.setRenderer(new HandlebarsRenderer(this.classLoader))
        this.controllers.add(new CustomTabDemoController(this, morpheus))
    }

    @Override
    public List<Permission> getPermissions() {
        return [
                Permission.build('Custom Tab Plugin','customTabPlugin', [Permission.AccessType.full])
        ]
    }

    /**
     * Called when a plugin is being removed from the plugin manager (aka Uninstalled)
     */
    @Override
    void onDestroy() {
        //nothing to do for now
    }
}
