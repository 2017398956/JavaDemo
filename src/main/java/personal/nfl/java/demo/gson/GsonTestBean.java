package personal.nfl.java.demo.gson;

import com.google.gson.annotations.SerializedName;

public class GsonTestBean {

    @SerializedName(value = "name" , alternate = {"NAme","Name" })
    private String name ;
    private String job ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
