import com.maven.common.utils.ReadFileUtil;

public class Test2 {
    public static void main(String[] args) {
        String stringBuffer = ReadFileUtil.readLine("D:\\new_file.html","UTF-8");
        System.out.println(stringBuffer.toString());
    }
}
