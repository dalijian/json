package com.lijian.r2dbc.react.router;

import com.lijian.r2dbc.react.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;



@Configuration
    public class RouterConfig {
        @Autowired
        private TimeHandler timeHandler;

        @Bean
        public RouterFunction<ServerResponse> timerRouter(){
            return RouterFunctions.route(RequestPredicates.GET("/time"), request -> timeHandler.getDate(request))
                    .andRoute(RequestPredicates.GET("/date"), request -> timeHandler.getDate(request));
        }

    }