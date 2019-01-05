package gullideckel.seasonhunter.ActivityMap;

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

public class LoadingScreen extends Fragment
{
    private IFragmentHandler listener;


//    // TODO: Rename and change types and number of parameters
//    public static LoadingScreen newInstance(String param1, String param2)
//    {
//
//    }





    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Context context = container.getContext();

        view = inflater.inflate(R.layout.frag_loading_screen, container, false);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();
        ImageView logo = (ImageView) view.findViewById(R.id.imgLogoRing);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation);
        logo.startAnimation(startRotateAnimation);
        return view;
    }



    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandler)
        {
            listener = (IFragmentHandler) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentHandler");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}
