package com.lsm.splashview.video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lsm.splashview.R;

public class VideoFragment extends Fragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private static final String VIDEO_RES = "res";
    private static final String SHOW_WELCOME = "show_welcome";
    private FullScreenVideoView videoView;
    private ImageView geNext;
    //是否显示按钮
    private boolean showWelcome;

    public static VideoFragment getInstance(int resId, boolean showWelcome) {
        VideoFragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(VIDEO_RES, resId);
        bundle.putBoolean(SHOW_WELCOME, showWelcome);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_guide, container, false);
        videoView = view.findViewById(R.id.videoview_guide);
        int videoRes = getArguments().getInt(VIDEO_RES);
        showWelcome = getArguments().getBoolean(SHOW_WELCOME);
        geNext = view.findViewById(R.id.go_next);
        geNext.setVisibility(showWelcome ? View.VISIBLE : View.INVISIBLE);

        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + videoRes);
        initEvent();
        return view;
    }

    private void initEvent() {
        videoView.setOnPreparedListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //准备播放回调
        if (videoView != null) {
            videoView.requestFocus();
            videoView.seekTo(0);
            videoView.start();
            videoView.setOnCompletionListener(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //播放完成监听
        videoView.requestFocus();
        videoView.seekTo(0);
        videoView.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }
}
