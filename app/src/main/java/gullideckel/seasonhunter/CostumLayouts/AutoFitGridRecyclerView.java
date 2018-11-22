package gullideckel.seasonhunter.CostumLayouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AutoFitGridRecyclerView extends RecyclerView
{
    private GridLayoutManager manager;
    private int columnWidth = -1;

    public AutoFitGridRecyclerView(Context context)
    {
        super(context);
        SetAutFitGridLayout(context, null);
    }

    public AutoFitGridRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        SetAutFitGridLayout(context, attrs);
    }

    public AutoFitGridRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        SetAutFitGridLayout(context, attrs);
    }

    public void SetAutFitGridLayout(Context context, AttributeSet attrs)
    {
        if (attrs != null)
        {
            // list the attributes we want to fetch
            int[] attrsArray =
                    {
                        android.R.attr.columnWidth
                    };

            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            //retrieve the value of the 0 index, which is columnWidth
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }
        manager = new GridLayoutManager(context, 1);
        setLayoutManager(manager);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec)
    {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0)
        {
            //The spanCount will always be at least 1
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }
}