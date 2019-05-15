import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherString {
    private static final String str1 = "^https://www.baidu.com/test/([a-z0-9]{10})/cat/([a-z0-9]{6})";
    private static final String str2 = "www.baidu.com/rabbit/test/${0}/monkey/${1}/dog/${0}/garfield/${1}";
    private static final String str3 = "https://www.baidu.com/test/a1b2c3d4e5/cat/f6g7h8";
    private static final String str4 = "(\\$\\{(\\d)\\})";

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();

        Pattern pattern = Pattern.compile(str1);
        Matcher matcher = pattern.matcher(str3);

        /**
         * 这里需要使用if，是要去匹配给定规则中的多个规则，每个规则获得的结果作为一个字串进行保存
         * 使用groupCount可以得到一共有几个规则
         */
        if (matcher.find()) {
            int i = 1;
            while (i <= matcher.groupCount()) {
                list1.add(matcher.group(i));
                i++;
            }
        }
        System.out.println(list1);
        //[a1b2c3d4e5, f6g7h8]

        ArrayList<String> list2 = new ArrayList<String>();
        Pattern pattern1 = Pattern.compile(str4);
        Matcher matcher1 = pattern1.matcher(str2);

        /**
         * 这里需要使用while遍历
         * 对应的是同一个规则，而要遍历给定字串中有多少个匹配
         * 使用group后iterator会移动到下一个匹配位置
         */
        while (matcher1.find()) {
            String finder = matcher1.group();
            if (!list2.contains(finder)) {
                list2.add(finder);
            }
        }

        System.out.println(list2);
        //[${0}, ${1}]

        String result = str2;
        int len = list1.size() < list2.size() ? list1.size() : list2.size();
        for (int i = 0; i < len; i++) {
            result = result.replace(list2.get(i), list1.get(i));
        }

        System.out.println(result);
        //www.baidu.com/rabbit/test/a1b2c3d4e5/monkey/f6g7h8/dog/a1b2c3d4e5/garfield/f6g7h8
    }
}
