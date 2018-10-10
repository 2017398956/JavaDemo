package personal.nfl.java.demo.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FundSearchContentHistory implements Serializable {

    public static final long serialVersionUID = 1 ;

    // 全部 模块的历史记录
    private SearchContentBean all = new SearchContentBean(4);
    private SearchContentBean other = new SearchContentBean() ;

    public SearchContentBean getAll(){
        return all ;
    }

    public static class SearchContentBean implements Serializable {

        public static final long serialVersionUID = 1 ;

        private static int maxLength = 6;// 默认最多保留 6 条搜索记录
        private List<String> values = new ArrayList<>();

        public SearchContentBean() {
        }

        public SearchContentBean(int maxLength) {
            this.maxLength = maxLength;
        }

        public boolean add(String searchContent) {
            if (searchContent == null) {
                return false;
            }
            // 如果有相同的则不再记录
            for (String temp : values) {
                if (searchContent.equals(temp)) {
                    return false;
                }
            }
            if (values.size() >= maxLength) {
                values.remove(0);
            }
            values.add(searchContent);
            return true;
        }

        public boolean remove(String searchContent){
            return values.remove(searchContent) ;
        }

        /**
         * 输出的时候反序，让后搜索的内容排在前面
         */
        public List<String> getValues() {
            System.out.println("输出");
            if(values.size() > maxLength){
                // 如果后续版本减小了 maxLength ,移除掉最先搜索的
                for(int i  = values.size() ; i > maxLength ; i-- ){
                    values.remove(0) ;
                }
            }
            List<String> tempList = new ArrayList<>();
            tempList.addAll(values);
            Collections.reverse(tempList);
            return tempList;
        }

    }

}
