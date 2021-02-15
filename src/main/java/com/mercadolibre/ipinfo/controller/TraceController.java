package com.mercadolibre.ipinfo.controller;

import com.mercadolibre.ipinfo.dto.RequestTrace;
import com.mercadolibre.ipinfo.dto.ResponseTrace;
import com.mercadolibre.ipinfo.service.TraceService;
import com.mercadolibre.ipinfo.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "Trace")
@Controller
@RequestMapping("trace")
public class TraceController {

    @Autowired
    private TraceService traceService;

    private static final Logger log = LoggerFactory.getLogger(TraceController.class);

    /**
     * Trace Ip Endpoint
     *
     * @return
     */
    @ApiOperation(value = "Trace info from IP",
            notes = "This Endpoint will return a complete trace from an IP",
            nickname = "traceIP")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful response"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity traceIP(@RequestBody RequestTrace requestTrace){
        log.info("IP INFO - TraceController - traceIP");

        return Util.getReturnController(traceService.traceIP(requestTrace));
    }

}
