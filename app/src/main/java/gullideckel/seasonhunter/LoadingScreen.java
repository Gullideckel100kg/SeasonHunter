package gullideckel.seasonhunter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.R;

public class LoadingScreen
{
    public View onCreateLoadingView(LayoutInflater inflater, Context context)
    {
        View view = inflater.inflate(R.layout.frag_loading_screen, null);

        view.setBackgroundColor(Color.TRANSPARENT);
        view.bringToFront();
        ImageView logo = (ImageView) view.findViewById(R.id.imgLogoRing);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation);
        logo.startAnimation(startRotateAnimation);
        return view;
    }
}
