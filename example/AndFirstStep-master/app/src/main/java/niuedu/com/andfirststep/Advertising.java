package niuedu.com.andfirststep;

//在列表控件中显示广告
public class Advertising {
    private String advertiser;//广告主
    private String content; //广告内容

    public Advertising(String advertiser, String content) {
        this.advertiser = advertiser;
        this.content = content;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
