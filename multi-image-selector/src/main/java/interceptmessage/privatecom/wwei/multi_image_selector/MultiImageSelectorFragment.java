package interceptmessage.privatecom.wwei.multi_image_selector;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.support.v4.app.LoaderManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import interceptmessage.privatecom.wwei.multi_image_selector.adapter.ImageGridAdapter;
import interceptmessage.privatecom.wwei.multi_image_selector.bean.Image;
import interceptmessage.privatecom.wwei.multi_image_selector.utils.FileUtils;

public class MultiImageSelectorFragment extends Fragment {
    public static final String TAG = "MultiImageSelectorFragment";
    // Single choice
    public static final int MODE_SINGLE = 0;
    // Multi choice
    public static final int MODE_MULTI = 1;

    /** Max image size，int，*/
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    /** Select mode，{@link #MODE_MULTI} by default */
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    /** Whether show camera，true by default */
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    /** Original data set */
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    private Callback mCallback;
    // image result data set
    private ArrayList<String> resultList = new ArrayList<>();
    private ImageGridAdapter mImageAdapter;
    private GridView mGridView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("The Activity must implement MultiImageSelectorFragment.Callback interface...");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mis_fragment_multi_image, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int mode = getArguments() == null ? MODE_MULTI : getArguments().getInt(EXTRA_SELECT_MODE);;
        if(mode == MODE_MULTI) {
            ArrayList<String> tmp = getArguments().getStringArrayList(EXTRA_DEFAULT_SELECTED_LIST);
            if(tmp != null && tmp.size()>0) {
                resultList = tmp;
            }
        }
        mImageAdapter = new ImageGridAdapter(getActivity(),true, 3);
        mGridView = (GridView) view.findViewById(R.id.grid);
        mGridView.setAdapter(mImageAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        showCameraAction();
                    } else {
                        Image image = (Image) adapterView.getAdapter().getItem(i);
                        selectImageFromGrid(image, mode);
                    }

            }
        });
    }
    // loaders
    private static final int LOADER_ALL = 0;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // load image data
        getActivity().getSupportLoaderManager().initLoader(LOADER_ALL, null, mLoaderCallback);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>()
    {        private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media._ID };
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = null;
            if(id==LOADER_ALL)
            {
                cursorLoader = new CursorLoader(getActivity(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_PROJECTION
                ,IMAGE_PROJECTION+">0 AND "+IMAGE_PROJECTION[3]+"=? OR "+IMAGE_PROJECTION[3]+" =? ",
                        new String[]{"image/jpeg","image/png"},IMAGE_PROJECTION[2]+"DESC");
            }

            return cursorLoader;
        }
        private boolean fileExists(String path)
        {
            if(!TextUtils.isEmpty(path))
            {
                return new File(path).exists();
            }
            return  false;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
              if(data!=null)
              {
                 if(data.getCount()>0)
                 {
                     List<Image> images = new ArrayList<>();
                     data.moveToFirst();
                     do{
                         String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                         String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                         long datetime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                         if(!fileExists(path))
                         {
                             continue;
                         }
                         Image image = null;
                         if(!TextUtils.isEmpty(name))
                         {
                             image = new Image(path,name,datetime);
                             images.add(image);
                         }

                     }while (data.moveToNext());
                     mImageAdapter.setData(images);
                     if(resultList != null && resultList.size()>0){
                         mImageAdapter.setDefaultSelected(resultList);
                     }
                 }
              }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
    private static final String KEY_TEMP_FILE = "key_temp_file";
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_TEMP_FILE, mTmpFile);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mTmpFile = (File) savedInstanceState.getSerializable(KEY_TEMP_FILE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CAMERA){
            if(resultCode == Activity.RESULT_OK) {
                if (mTmpFile != null) {
                    if (mCallback != null) {
                        mCallback.onCameraShot(mTmpFile);
                    }
                }
            }else{
                // delete tmp file
                while (mTmpFile != null && mTmpFile.exists()){
                    boolean success = mTmpFile.delete();
                    if(success){
                        mTmpFile = null;
                    }
                }
            }
        }
    }

    /**
     * Open camera
     */
    private File mTmpFile;
    private static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 110;
    private static final int REQUEST_CAMERA = 100;
    private void showCameraAction() {
         if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                 PackageManager.PERMISSION_GRANTED)
         {
             requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);
         }
         else
         {
             Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             if(intent.resolveActivity(getActivity().getPackageManager())!=null)
             {
                 try
                 {
                     mTmpFile = FileUtils.createTmpFile(getActivity());
                 }catch (IOException e)
                 {
                     e.printStackTrace();
                 }
                 if (mTmpFile != null && mTmpFile.exists()) {
                     intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
                     startActivityForResult(intent, REQUEST_CAMERA);
                 } else {
                     Toast.makeText(getActivity(),"图片错误", Toast.LENGTH_SHORT).show();
                 }
             }
             else
             {
                 Toast.makeText(getActivity(), "没有系统相机", Toast.LENGTH_SHORT).show();
             }
         }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_STORAGE_WRITE_ACCESS_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showCameraAction();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    /**
     * notify callback
     * @param image image data
     */
    private void selectImageFromGrid(Image image, int mode) {
        if (image != null) {
            if(mode == MODE_MULTI) {
                if (resultList.contains(image.path)) {
                    resultList.remove(image.path);
                    if (mCallback != null) {
                        mCallback.onImageUnselected(image.path);
                    }
                } else {
                    if(9 == resultList.size()){
                        Toast.makeText(getActivity(),"已经达到最高选择数量", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultList.add(image.path);
                    if (mCallback != null) {
                        mCallback.onImageSelected(image.path);
                    }
                }
                mImageAdapter.select(image);
            }
        }
    }

    public interface Callback{
        void onImageSelected(String path);
        void onImageUnselected(String path);
        void onCameraShot(File imageFile);
    }

}
