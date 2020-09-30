
import cn.hutool.http.HttpUtil;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.maven.common.entity.namesilo7005.TbDomain;
import com.maven.common.utils.HtmlUtil;
import com.maven.common.utils.HttpClientUtil;
import com.maven.common.utils.ReadFileUtil;
import com.maven.namesilo7005.entity.CheckIdData;
import com.maven.namesilo7005.entity.DataResponse;
import com.maven.namesilo7005.entity.ResultData;
import com.maven.namesilo7005.utils.RequestParameterUtil;





import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        //读取域名后缀文件
        String htmlModel = ReadFileUtil.readLine("D:\\new_file.html", "UTF-8");
        //获得域名后缀集合
        List<String> domainSuffixList = HtmlUtil.domainSuffix(htmlModel, "UTF-8",3);
            //工具创建form表数据
            Map<String, List<String>> requestMap = RequestParameterUtil.getForms("zz",domainSuffixList);
            //工具请求
            String responseStr = HttpClientUtil.Post("https://www.namesilo.com/public/api/domains/bulk-check", requestMap);
            //返回json字符串转为实体bean
            DataResponse dataResponse1 = JSONUtil.toBean(responseStr, DataResponse.class);
            Object checkData = dataResponse1.getData();
            //转换实体
            CheckIdData checkIdData = JSONObject.parseObject(JSONObject.toJSONString(checkData), CheckIdData.class);

            System.out.println("checkId实体类" + checkIdData.toString());
            //获取id
            String checkId = checkIdData.getCheckId();

            //获取数据get
            String resp = HttpUtil.get("https://www.namesilo.com/public/api/domains/results/" + checkId);
            DataResponse dataResponse2 = JSONUtil.toBean(resp, DataResponse.class);
            Object resultDataObj = dataResponse2.getData();
            //转换实体
            ResultData resultData = JSONObject.parseObject(JSONObject.toJSONString(resultDataObj), ResultData.class);
            List<TbDomain> domains = resultData.getDomains();
            //转换实体
            System.out.println("返回数据实体类" + domains);





    }
}
