package org.dalol.collectionlayoutview.decorator;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridItemsMarginDecorator extends RecyclerView.ItemDecoration {

    private final int mSpaceSize;
    private final int mSpanCount;

    public GridItemsMarginDecorator(int spaceSize, int spanCount) {
        mSpaceSize = spaceSize;
        mSpanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        if (position < mSpanCount) {
            outRect.top = mSpaceSize;
        } else {
            outRect.top = 0;
        }

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int column = params.getSpanIndex();

        outRect.left = mSpaceSize - column * mSpaceSize / mSpanCount;
        outRect.right = (column + 1) * mSpaceSize / mSpanCount;
        outRect.bottom = mSpaceSize;
    }
}
