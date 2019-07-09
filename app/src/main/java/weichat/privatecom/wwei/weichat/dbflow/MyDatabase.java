package weichat.privatecom.wwei.weichat.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Administrator on 2019/7/9.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    //数据库名称
    public static final String NAME = "WeiChatDatabase";
    //数据库版本号,如果以后要修改任意表的结构，为避免与旧版本数据库冲突一定要修改版本号，且保证版本号只升不降。
    public static final int VERSION = 1;
}
