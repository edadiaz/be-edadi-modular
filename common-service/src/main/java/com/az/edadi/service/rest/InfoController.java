package com.az.edadi.service.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

        @RequestMapping
        public String info() {
            return "Common service is running on development mode";
        }
}
