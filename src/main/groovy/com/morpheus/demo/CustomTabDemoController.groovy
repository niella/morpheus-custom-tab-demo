package com.morpheus.demo

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.model.Permission
import com.morpheusdata.views.HTMLResponse
import com.morpheusdata.views.JsonResponse
import com.morpheusdata.views.ViewModel
import com.morpheusdata.web.PluginController
import com.morpheusdata.web.Route
import groovy.util.logging.Slf4j


@Slf4j
class CustomTabDemoController implements PluginController {

    Plugin plugin
    MorpheusContext morpheus
    String code = "customTabController"
    String name = "Custom Tab Controller"


    CustomTabDemoController(Plugin plugin, MorpheusContext morpheus) {
        this.plugin = plugin
        this.morpheus = morpheus
    }


    @Override
    List<Route> getRoutes() {
        log.info("returning Custom tab routes...")
        def routes = [
                Route.build("/customTabController/example", "example", Permission.build("customTabPlugin", "full"))
        ]
        return routes
    }

    def example(ViewModel<String> model) {
        log.info("TARGET METHOD REACHED")

        return HTMLResponse.success("<h1>Hello World</h1>")
    }

    def json(ViewModel<Map> model) {
        Map simpleMap = [serverid: "abc-123", other: model.object.someData]
        return JsonResponse.of(simpleMap)
    }


    @Override
    MorpheusContext getMorpheus() {
        return morpheus
    }

    @Override
    Plugin getPlugin() {
        return plugin
    }

    @Override
    String getCode() {
        return code
    }

    @Override
    String getName() {
        return name
    }
}
