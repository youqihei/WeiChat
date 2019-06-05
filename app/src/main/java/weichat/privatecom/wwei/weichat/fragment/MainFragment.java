package weichat.privatecom.wwei.weichat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import butterknife.BindView;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.LoginActivity;

/**
 * Created by Administrator on 2019/4/23.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_register)
    Button bt_register;

    private static MainFragment mainfragment;
    public static BaseFragment newInstance()
    {
        if(mainfragment==null)
        {
            mainfragment = new MainFragment();
        }
        return mainfragment;
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {

      bt_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getHodingActivity(), LoginActivity.class);
              Bundle data = new Bundle();
              data.putString("name","wei");
              intent.putExtras(data);
              startActivityForResult(intent,0);
          }
      });

    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
   private int[] postorder ;
    private int[] inorder;
    private int postorderlength,inorderlength;
    public TreeNode buildTree(int[] postorder, int[] inorder) {
       if(postorder.length==0||inorder.length==0||postorder.length!=inorder.length)
       {
           return null;
       }
       this.postorder = postorder;
       this.inorder = inorder;
        postorderlength = postorder.length - 1;
        inorderlength = inorder.length-1;
       int post_start = 0;
       int post_end = postorder.length-1;
       int in_start = 0;
       int in_end = inorder.length-1;
        TreeNode result = buildtree(post_start,post_end,in_start,in_end);
       return result;
    }
    public TreeNode buildtree(int post_start,int post_end,int in_start,int in_end)
    {
        if(post_start>post_end||in_start>in_end||post_start<0||in_start<0||post_start>postorderlength||in_start>inorderlength)
        {
            return null;
        }
        int  inordernum = postorder[post_end];
      TreeNode cur = new TreeNode(inordernum);
      int rightnum = 0;
        for(int i=in_start;i<=in_end;i++)
        {
            if(inordernum==inorder[i])
            {
                rightnum = in_end-i;
                break;
            }
        }
        cur.right = buildtree(post_end-rightnum,post_end-1,in_end-rightnum+1,in_end);
        cur.left = buildtree(post_start,post_end-rightnum-1,in_start,in_end-rightnum-1);
        return cur;
    }
    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }
}
