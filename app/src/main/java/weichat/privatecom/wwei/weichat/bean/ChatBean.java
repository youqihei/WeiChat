package weichat.privatecom.wwei.weichat.bean;

/**
 * Created by Administrator on 2019/6/18.
 */

public class ChatBean {
    private String imv;
    private String title;
    private String message_id;
    private String content;
   public ChatBean()
   {}

   public ChatBean(String imv,String title,String content,String message_id)
   {
       this.imv = imv;
       this.title = title;
       this.content = content;
       this.message_id= message_id;
   }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImv() {
        return imv;
    }

    public void setImv(String imv) {
        this.imv = imv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }
}
