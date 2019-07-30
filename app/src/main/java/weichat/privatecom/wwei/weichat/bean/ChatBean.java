package weichat.privatecom.wwei.weichat.bean;

/**
 * Created by Administrator on 2019/6/18.
 */

public class ChatBean {
    private String imv;
    private String title;
    private String content;
    private String groupid;
    private String userid;
    private String friendid;
    private int type;
   public ChatBean()
   {}

   public ChatBean(int type,String groupid,String userid,String friendid,String imv,String title,String content)
   {
       this.type = type;
       this.groupid = groupid;
       this.userid = userid;
       this.friendid = friendid;
       this.imv = imv;
       this.title = title;
       this.content = content;
   }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

}
