package com.mercadolibre.ipinfo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Stats")
@Controller
@RequestMapping("stats")
public class StatsController {

    private static final Logger log = LoggerFactory.getLogger(StatsController.class);

    /**
     * Stats Endpoint
     *
     * @return
     */
    @ApiOperation(value = "Stats from application",
            notes = "This Endpoint will return a resume for application using",
            nickname = "stats")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful response"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String traceIP(){
        log.info("IP INFO - StatsController - stats");

        return "Hola Mundo!";
    }

}
