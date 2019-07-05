package weichat.privatecom.wwei.weichat.bean;

/**
 * Created by Administrator on 2019/6/18.
 */

public class ChatBean {
    private String imv;
    private String title;
    private String friend_id;
    private String content;

   public ChatBean(String imv,String title,String content,String friend_id)
   {
       this.imv = imv;
       this.title = title;
       this.content = content;
       this.friend_id = friend_id;
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

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }
}
