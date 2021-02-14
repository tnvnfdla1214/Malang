package org.hugoandrade.calendarviewapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import org.hugoandrade.calendarviewapp.helpers.CubeAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author kakajika
 * @since 2015/11/27
 */
public class ExampleFragment extends Fragment {

    @IntDef({DATE, CUBE})
    public @interface AnimationStyle {}
    public static final int DATE     = 0;
    public static final int CUBE     = 2;

    @IntDef({NODIR, UP, DOWN, LEFT, RIGHT})
    public @interface AnimationDirection {}
    public static final int NODIR = 0;
    public static final int UP    = 1;
    public static final int DOWN  = 2;
    public static final int LEFT  = 3;
    public static final int RIGHT = 4;

    private static final long DURATION = 500;

    //시작은 큐브로 세팅함
    @AnimationStyle
    public static int sAnimationStyle;

    @BindView(R.id.textAnimationStyle)
    TextView mTextAnimationStyle;

    public static ExampleFragment newInstance(@AnimationDirection int direction, int data) {
        ExampleFragment f = new ExampleFragment();
        f.setArguments(new Bundle());
        f.getArguments().putInt("direction", direction);
        f.getArguments().putInt("data", data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_anim, container, false);
        int color = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                              (int) Math.floor(Math.random() * 128) + 64,
                              (int) Math.floor(Math.random() * 128) + 64);
        view.setBackgroundColor(color);
        ButterKnife.bind(this, view);
        setAnimationStyleText();
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        switch (sAnimationStyle) {
            case CUBE:
                switch (getArguments().getInt("direction")) {
                    case LEFT:
                        return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
                }
                break;
            case DATE:
                switch (getArguments().getInt("direction")) {
                    case UP:
                        return CubeAnimation.create(CubeAnimation.UP, enter, DURATION);
                    case DOWN:
                        return CubeAnimation.create(CubeAnimation.DOWN, enter, DURATION);
                    case LEFT:
                        return CubeAnimation.create(CubeAnimation.LEFT, enter, DURATION);
                    case RIGHT:
                        return CubeAnimation.create(CubeAnimation.RIGHT, enter, DURATION);
                }
                break;
        }
        return null;
    }

//    @SuppressWarnings("unused")
//    @OnClick(R.id.buttonUp)
//    void onButtonUp() {
//        getArguments().putInt("direction", UP);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.layout_main, ExampleFragment.newInstance(UP));
//        ft.commit();
//    }
//
//    @SuppressWarnings("unused")
//    @OnClick(R.id.buttonDown)
//    void onButtonDown() {
//        getArguments().putInt("direction", DOWN);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.layout_main, ExampleFragment.newInstance(DOWN));
//        ft.commit();
//    }
//
    @SuppressWarnings("unused")
    @OnClick(R.id.buttonLeft)
    public void onButtonLeft() {
        switch (sAnimationStyle) {
            case DATE:
                sAnimationStyle = CUBE;
                break;
            case CUBE:
                sAnimationStyle = DATE;
                break;
        }
        getArguments().putInt("direction", LEFT);
        getArguments().putInt("data", sAnimationStyle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, ExampleFragment.newInstance(LEFT, sAnimationStyle));
        ft.commit();
    }
//
//    @SuppressWarnings("unused")
//    @OnClick(R.id.buttonRight)
//    void onButtonRight() {
//        getArguments().putInt("direction", RIGHT);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.layout_main, ExampleFragment.newInstance(RIGHT));
//        ft.commit();
//    }
//
//    @SuppressWarnings("unused")
//    @OnClick(R.id.textAnimationStyle)
//    public void switchAnimationStyle(View view) {
//        @AnimationStyle int[] styles;
//        styles = new int[]{CUBE};
//        for (int i = 0; i<styles.length-1; ++i) {
//            if (styles[i] == sAnimationStyle) {
//                setAnimationStyle(styles[i+1]);
//                return;
//            }
//        }
//        setAnimationStyle(CUBE);
//    }

    public void setAnimationStyle(@AnimationStyle int style) {
        if (sAnimationStyle != style) {
            sAnimationStyle = style;
            setAnimationStyleText();  //이름 적기
            Snackbar.make(getView(), "Animation Style is Changed", Snackbar.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void setAnimationStyleText() {
        switch (sAnimationStyle) {
            case DATE:
                mTextAnimationStyle.setText("DATE");
                break;
            case CUBE:
                mTextAnimationStyle.setText("Cube");
                break;
        }
    }

}
