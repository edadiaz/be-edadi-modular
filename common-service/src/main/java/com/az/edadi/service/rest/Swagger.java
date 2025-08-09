package com.az.edadi.service.rest;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1")
public class Swagger {

    @GetMapping("/docs")
    public void getSwaggerUI(HttpServletResponse response) throws IOException {
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                  <meta charset="UTF-8" />
                  <title>Swagger UI - CDN</title>
                  <link
                    href="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/4.18.2/swagger-ui.css"
                    rel="stylesheet"
                  />
                  <style>
                    body {
                      margin: 0;
                      padding: 0;
                    }
                    #swagger-ui {
                      margin: 0;
                    }
                  </style>
                </head>
                <body>
                  <div id="swagger-ui"></div>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/4.18.2/swagger-ui-bundle.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/4.18.2/swagger-ui-standalone-preset.js"></script>
                  <script>
                    window.onload = function () {
                      SwaggerUIBundle({
                        url: "https://develop.edadi.az/api/v1/api-docs",
                        dom_id: "#swagger-ui",
                        presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
                        layout: "StandaloneLayout",
                        deepLinking: true,
                      });
                    };
                  </script>
                </body>
                </html>
                """;

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(html);
    }


}
