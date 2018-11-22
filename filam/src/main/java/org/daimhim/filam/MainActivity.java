package org.daimhim.filam;

import android.content.res.AssetFileDescriptor;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSvSurfaceview;
    private MediaPlayer mMediaPlayer;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mSvSurfaceview = (SurfaceView) findViewById(R.id.sv_surfaceview);
        mSvSurfaceview.getHolder().addCallback(this);
        mMediaPlayer = new MediaPlayer();
        mSvSurfaceview.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mMediaPlayer.isPlaying()) {
                            int lI = mMediaPlayer.getDuration() - mMediaPlayer.getCurrentPosition();
                            if (lI < 300) {
                                mMediaPlayer.stop();
                                break;
                            }
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        play();
        Timber.i("surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Timber.i("surfaceChanged format:%s width:%s height:%s", format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Timber.i("surfaceDestroyed");
        mCurrentPosition = mMediaPlayer.getCurrentPosition();
        holder.getSurface().release();
    }

    private void play() {
        try {
            mMediaPlayer.setDisplay(mSvSurfaceview.getHolder());
            mMediaPlayer.reset();//重置为初始状态
            mMediaPlayer.setDisplay(mSvSurfaceview.getHolder());//设置video影片以surfaceviewholder播放
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音乐流的类型
            AssetFileDescriptor lAssetFileDescriptor = getResources().openRawResourceFd(R.raw.adbasd);
            mMediaPlayer.setDataSource(lAssetFileDescriptor.getFileDescriptor(), lAssetFileDescriptor.getStartOffset(), lAssetFileDescriptor.getDeclaredLength());//设置路径
            mMediaPlayer.prepare();//缓冲
            mMediaPlayer.start();//播放
        } catch (Exception e) {
            Timber.i(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
