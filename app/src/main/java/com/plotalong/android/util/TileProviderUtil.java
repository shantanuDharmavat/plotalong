package com.plotalong.android.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shantanu on 17/8/17.
 */

public class TileProviderUtil implements TileProvider {
    private static final int TILE_WIDTH = 256;
    private static final int TILE_HEIGHT = 256;
    private static final int BUFFER_SIZE = 16 * 1024;
    AssetManager mAssets;
    Context mContext;
    private String TAG = TileProviderUtil.class.getSimpleName();
    private Paint opacityPaint = new Paint();

    public TileProviderUtil(AssetManager assets, int alphaValue, Context context) {
        mAssets = assets;
        mContext = context;
        setOpacity(alphaValue);
    }

    @Override
    public Tile getTile(int x, int y, int zoom) {
        ByteArrayOutputStream stream = null;
        if (!checkTileExists(x, y, zoom)) {
            return null;
        } else {
            Log.e(TAG, "getTile: Zoom= " + zoom + " X= " + x + " Y= " + y);
            byte[] image = readTileImage(x, y, zoom);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            bmp = adjustOpacity(bmp);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return image == null ? null : new Tile(TILE_WIDTH, TILE_HEIGHT, byteArray);
        }
    }

    /**
     * Read tile from storage path of tiles
     *
     * @param x
     * @param y
     * @param zoom
     * @return byte[]
     */
    private byte[] readTileImage(int x, int y, int zoom) {
        InputStream in = null;
//        String selectedLayer = "layers/contour/";
//        String selectedLayer = "layers/WindWardsCountour/";
        String selectedLayer = "layers/";
        ByteArrayOutputStream buffer = null;
        //SD card path
        Singleton singleton = Singleton.getInstance();

        String path = GlobalConstant.PROJECT_FOLDER_PATH
                + singleton.getSelectedProjectPhase().getPhas_devl_id() + "/"
                + singleton.getSelectedProjectPhase().getPhas_proj_id() + "/"
                + singleton.getSelectedProjectPhase().getPhas_id() + "/"
                + selectedLayer;

        File file = new File(path);
        String endFile="";
        String[] fileList = file.list();

        for (int k = 0; k < fileList.length; k++){
            if (new File(path,fileList[k]).isDirectory())
                endFile = fileList[k];
        }
        path = path.concat(endFile+"/");

//        String path=AppSetting.getProjectPath(mContext,AppSetting.PROJECT_PATH)+ selectedLayer;
        Log.e(TAG, "tiles Path::" + path);
        try {
            Log.e(TAG, "readTileImage: " + getTileFilename(x, y, zoom));
            String tilePath = path+getTileFilename(x, y, zoom);
            Log.e(TAG, "readTileImage: tilePath: " + tilePath );

            in = new FileInputStream(tilePath);
            buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[BUFFER_SIZE];

            while ((nRead = in.read(data, 0, BUFFER_SIZE)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) try {
                in.close();
            } catch (Exception ignored) {
            }
            if (buffer != null) try {
                buffer.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Read File Name of file
     *
     * @param x
     * @param y
     * @param zoom
     * @return file name string
     */
    private String getTileFilename(int x, int y, int zoom) {
//	        return "map/"+zoom + '/' + x + '/' + y + ".png";
        String path = null;
        try {
            PhaseDataModel model = Singleton.getInstance().getSelectedProjectPhase();

            StringBuilder builder = new StringBuilder();

            builder.append(model.getPhas_devl_id());
            builder.append(model.getPhas_proj_id());
            builder.append(model.getPhas_id());
            builder.append("cb_afde~");
            builder.append("w3de~");
            builder.append(zoom);
            builder.append("de~");
            builder.append(x);
            builder.append("de~");
            builder.append(y);
            builder.append("&_.");
            builder.append("png");

//            path = model.getComp_proj_developer_id() + model.getComp_proj_id() + model.getProj_phas_id() + "cb_afde~"
//                    + "w3de~" + zoom + "de~" + x + "de~" + y + "&_." + "png";

//            Log.e(TAG, "getTileFilename: PATH STRING: " + path);
            path = builder.toString();
            Log.e(TAG, "getTileFilename: PATH BUILDER: " + builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getTileFilename: EXCEPTION" + e);
        }
        return path;
    }

    /**
     * Check tiles present or not
     *
     * @param x
     * @param y
     * @param zoom
     * @return true if present otherwise false
     */
    private boolean checkTileExists(int x, int y, int zoom) {
        int minZoom = 14;
        int maxZoom = 19;

        return !(zoom < minZoom || zoom > maxZoom);

    }

    /**
     * Adjust opacity of bitmap
     *
     * @param bitmap
     * @return Bitmap
     */
    private Bitmap adjustOpacity(Bitmap bitmap) {
        Bitmap adjustedBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(adjustedBitmap);
        canvas.drawBitmap(bitmap, 0, 0, opacityPaint);

        return adjustedBitmap;
    }

    /**
     * set Opacity
     *
     * @param opacity
     */
    public void setOpacity(int opacity) {
        int alpha = (int) Math.round(opacity * 2.55);    // 2.55 = 255 * 0.01
        opacityPaint.setAlpha(alpha);
    }
}
