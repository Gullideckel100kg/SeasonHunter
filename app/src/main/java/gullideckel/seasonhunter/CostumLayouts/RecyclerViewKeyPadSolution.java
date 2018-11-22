package gullideckel.seasonhunter.CostumLayouts;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

public class RecyclerViewKeyPadSolution
{
    private RecyclerView recyclerView;
    private boolean isKeyPad = false;

    public RecyclerViewKeyPadSolution(RecyclerView recyclerView)
    {
        this.recyclerView = recyclerView;
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    //This is only suggested if Recyclerview takes the whole screen.
    //If Views get added under RecyclerView it has to be sure that they take less then 15%(ratio) of the display size
    private static final double RATIO = 0.15;

    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout()
        {

            Rect r = new Rect();
            recyclerView.getWindowVisibleDisplayFrame(r);
            int screenHeight = recyclerView.getRootView().getHeight();

            int keypadHeight = screenHeight - r.bottom;

            if (keypadHeight > screenHeight * RATIO)
            {
                isKeyPad = true;
                OnKeyPadShowing();
            }

            if (isKeyPad && keypadHeight == 0)
            {
                isKeyPad = false;
                OnKeyPadDisappearing();
            }
        }
    };

    protected  void OnKeyPadDisappearing() { }

    protected void OnKeyPadShowing() { }
}
