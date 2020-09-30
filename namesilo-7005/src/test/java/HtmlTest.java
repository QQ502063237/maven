import com.maven.common.utils.HtmlUtil;
import com.maven.common.utils.ReadFileUtil;

import java.util.List;

public class HtmlTest {
    public static void main(String[] args) {
        String htmlModel = ReadFileUtil.readLine("D:\\new_file.html", "UTF-8");
        List<String> domainS = HtmlUtil.domainSuffix(htmlModel, "UTF-8",3);
        System.out.println(domainS);
    }
}
