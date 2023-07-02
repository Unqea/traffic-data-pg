
package com.traffic.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * Api文档
 * </p>
 *
 *
 * @date 2022-01-25
 */
@Controller
@RequestMapping("/docs")
@ApiIgnore
public class ApiDocController extends BaseController {

    /**
     * swaggerUI
     */
    @GetMapping("")
    public String swaggerUI() {
        return "redirect:/doc.html";
    }

}
