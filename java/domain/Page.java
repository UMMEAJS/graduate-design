package domain;

import java.util.List;

public class Page<Object> {
    private int currPage;
    private int totalPage;
    private List<Object> beanList;
    private String url;

    public int getCurrPage() {
        return currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<Object> getBeanList() {
        return beanList;
    }

    public String getUrl() {
        return url;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setBeanList(List<Object> beanList) {
        this.beanList = beanList;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
