package jollyroger.enterprise_posture;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by p4071825 on 08/05/16.
 */
public class Preview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private Camera camera;


    Preview(Context con, Camera c) {
        super(con);
        camera = c;
        sh = getHolder();
        sh.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            Log.d(null, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
