package lecture.day6.booksearch.vo;

public class Book { // table 이름이랑 똑같이 짓는 게 좋음
    // table의 모든 attribute 가지는 것이 좋음 -> entity
    private String bisbn;
    private String btitle;
    private String bdate;
    private int bpage;
    private int bprice;
    private String bauthor;
    private String btranslator;
    private String bpublisher;
    private String bimgurl;

    public Book(String bisbn, String btitle, int bprice, String bauthor) {
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bprice = bprice;
        this.bauthor = bauthor;
    }

    public Book(String bisbn, String btitle, String bdate, int bpage, int bprice, String bauthor, String btranslator, String bpublisher, String bimgurl) {
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bdate = bdate;
        this.bpage = bpage;
        this.bprice = bprice;
        this.bauthor = bauthor;
        this.btranslator = btranslator;
        this.bpublisher = bpublisher;
        this.bimgurl = bimgurl;
    }

    public String getBisbn() {
        return bisbn;
    }

    public String getBtitle() {
        return btitle;
    }

    public String getBdate() {
        return bdate;
    }

    public int getBpage() {
        return bpage;
    }

    public int getBprice() {
        return bprice;
    }

    public String getBauthor() {
        return bauthor;
    }

    public String getBtranslator() {
        return btranslator;
    }

    public String getBpublisher() {
        return bpublisher;
    }

    public String getBimgurl() {
        return bimgurl;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setBpage(int bpage) {
        this.bpage = bpage;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setBtranslator(String btranslator) {
        this.btranslator = btranslator;
    }

    public void setBpublisher(String bpublisher) {
        this.bpublisher = bpublisher;
    }

    public void setBimgurl(String bimgurl) {
        this.bimgurl = bimgurl;
    }
}
