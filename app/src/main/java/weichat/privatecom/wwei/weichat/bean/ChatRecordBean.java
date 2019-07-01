package weichat.privatecom.wwei.weichat.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/1.
 */

public class ChatRecordBean {
    private List<Group> groups;
    private List<User> users;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public class Group
    {
        private String groupid;
        private String groupname;
        private String groupphoto;
        private GroupContent groupcontent;

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getGroupphoto() {
            return groupphoto;
        }

        public void setGroupphoto(String groupphoto) {
            this.groupphoto = groupphoto;
        }
        public GroupContent getGroupcontent() {
            return groupcontent;
        }

        public void setGroupcontent(GroupContent groupcontent) {
            this.groupcontent = groupcontent;
        }

        public class GroupContent
        {
            private List<String> group_names;
            private List<String> group_ids;
            private List<String> group_photos;
            private List<String> content;
            public List<String> getGroup_names() {
                return group_names;
            }
            public void setGroup_names(List<String> group_names) {
                this.group_names = group_names;
            }
            public List<String> getGroup_ids() {
                return group_ids;
            }
            public void setGroup_ids(List<String> group_ids) {
                this.group_ids = group_ids;
            }
            public List<String> getGroup_photos() {
                return group_photos;
            }
            public void setGroup_photos(List<String> group_photos) {
                this.group_photos = group_photos;
            }
            public List<String> getContent() {
                return content;
            }
            public void setContent(List<String> content) {
                this.content = content;
            }


        }
    }

    public class User
    {
        private String userid;
        private String username;
        private String userphoto;
        private List<String> usercontent;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserphoto() {
            return userphoto;
        }

        public void setUserphoto(String userphoto) {
            this.userphoto = userphoto;
        }

        public List<String> getUsercontent() {
            return usercontent;
        }

        public void setUsercontent(List<String> usercontent) {
            this.usercontent = usercontent;
        }
    }
}
