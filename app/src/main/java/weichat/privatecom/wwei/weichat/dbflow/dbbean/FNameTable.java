package weichat.privatecom.wwei.weichat.dbflow.dbbean;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import weichat.privatecom.wwei.weichat.dbflow.MyDatabase;

/**
 * Created by Administrator on 2019/7/9.
 */
@Table(database = MyDatabase.class)
public class FNameTable extends BaseModel {
    @Column
    @PrimaryKey
    int userid;
    @Column
    public String username;
    @Column
    public String userphoto;
    @Column
    public String username_en;

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

    public String getUsername_en() {
        return username_en;
    }

    public void setUsername_en(String username_en) {
        this.username_en = username_en;
    }
}
