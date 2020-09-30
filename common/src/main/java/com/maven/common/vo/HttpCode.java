package com.maven.common.vo;

/**
 *
 * @description:接口返回统一状态码   也可以是hutools中提供的code    cn.hutool.http.HttpStatus
 * @param: com.wssbdc.system.common.api.vo.HttpCode
 * @author: zhanglei
 * @date: 2019/04/14
 */
public interface HttpCode {




    /**错误信息 返回状态码*/
    Integer SYS_ERROR_CODE = 500;
    /**正确信息 返回状态码*/
    Integer SYS_OK_CODE = 200;
    /**刷新token状态码*/
    Integer REFRESH_TOKEN_CODE = 230;
    /**无权限信息 返回状态码*/
    Integer SYS_NO_AUTHZ = 510;
    /**登录失败信息 返回状态码*/
    Integer SYS_NO_AUTHEN = 401;
    /**token认证失败 返回状态码*/
    Integer SYS_TOKEN_ERROR= 530;
    /**  未找到路径  404 **/
    Integer NOT_FOUND_ERROR= 404;








    /**返回成功信息*/
    String OK_MESSAGE="操作成功";
    /**返回失败信息*/
    String ERROR_MESSAGE="操作失败";

}
