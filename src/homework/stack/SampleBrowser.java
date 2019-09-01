package homework.stack;

/**
 * 模拟浏览器前进、后退
 */
public class SampleBrowser {
    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

    private LinkedStack backStack = new LinkedStack();
    private LinkedStack forwardStack = new LinkedStack();
    private String currentPage;

    /**
     * 打开页面
     * @param url
     */
    public void open(String url){
        if(this.currentPage!=null){
            backStack.push(currentPage);
        }
        forwardStack.clear();
        showUrl(url, "Open");
    }


    private void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix+" page = "+url);
    }

    /**
     * 是否可以后退
     * @return
     */
    public boolean canGoBack(){
        return !backStack.isEmpty();
    }

    /**
     * 是否可以前进
     * @return
     */
    public boolean canGoForward(){
        return !forwardStack.isEmpty();
    }

    /**
     * 后退
     * @return
     */
    public String goBack(){
        if(this.canGoBack()){
            forwardStack.push(this.currentPage);
            String url = backStack.pop();
            showUrl(url, "Go back");
            return url;
        }
        System.out.println("Can not go back , no pages behind.");
        return null;
    }

    /**
     * 前进
     * @return
     */
    public String goForward(){
        if(canGoForward()){
            backStack.push(this.currentPage);
            String url = forwardStack.pop();
            showUrl(url, "Go Forward");
            return url;
        }
        System.out.println("Can not go forward , no pages ahead.");
        return null;
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

}
