package personal.nfl.java.demo.gson;

import personal.nfl.java.demo.util.GsonUtil;

public class GsonTest {

    public static void main(String[] args) {
//        GsonTestBean gsonTestBean = new GsonTestBean() ;
//        gsonTestBean.setName("Bob");
//        gsonTestBean.setJob("教师");
//        String gsonString = GsonUtil.object2String(gsonTestBean) ;
//        System.out.println(gsonString);


        testZhujie();
    }

    private static void testZhujie() {
        String str = "{\"Name\":\"Bob2\",\"name\":\"Bob\",\"NAme\":\"Bob3\",\"job\":\"教师\"}";
        GsonTestBean gsonTestBean = (GsonTestBean) GsonUtil.string2Object(str, GsonTestBean.class);
        System.out.println(gsonTestBean.getJob());
        System.out.println(gsonTestBean.getName());
        System.out.println(GsonUtil.object2String(gsonTestBean));
    }
}
