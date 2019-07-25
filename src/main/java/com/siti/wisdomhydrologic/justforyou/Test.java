package com.siti.wisdomhydrologic.justforyou;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by DC on 2019/7/25.
 *
 * @data ${DATA}-16:28
 */
@RestController
public class Test {

    //用户传过来的模板文件 存储字符串 返回仍旧为字符串
    @RequestMapping(value = "saveText", method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "前台上传的模板文件",
            notes = "前台转换模板成字符串")
    @ApiImplicitParam(name = "text", value = "前台模板转换之后的字符串", required = true, dataType = "String")
    public String saveText(@RequestParam("text") String text) {
        return JSON.toJSONString(text);
    }
}
