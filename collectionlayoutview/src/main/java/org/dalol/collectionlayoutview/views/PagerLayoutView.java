package org.dalol.collectionlayoutview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.dalol.collectionlayoutview.ItemViewHolder;
import org.dalol.collectionlayoutview.adapter.CollectionViewAdapter;
import org.dalol.collectionlayoutview.base.BasePagerAdapter;
import org.dalol.collectionlayoutview.transformer.DepthPageTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by filippo on 23/01/2018.
 */

public class PagerLayoutView extends ViewPager {

    public PagerLayoutView(@NonNull Context context) {
        super(context);
    }

    public PagerLayoutView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public <T, VH extends ItemViewHolder<T>> void setLayoutAdapter(@NotNull CollectionViewAdapter<T, VH> itemLayoutAdapter,
                                                                   List<T> items, int numberOfColumns, int numberOfRows, int spacing) {
        PagerLayoutAdapter<T, VH> pagerLayoutAdapter = new PagerLayoutAdapter<>(itemLayoutAdapter, items, numberOfColumns, numberOfRows, spacing);
        setAdapter(pagerLayoutAdapter);
        setPageTransformer(true, new DepthPageTransformer());
    }

    private class PagerLayoutAdapter<T, VH extends ItemViewHolder<T>> extends BasePagerAdapter<T, GridListLayoutView> {

        private final CollectionViewAdapter<T, VH> itemLayoutAdapter;
        private final int mSpacing;

        PagerLayoutAdapter(@NotNull CollectionViewAdapter<T, VH> itemLayoutAdapter, List<T> items, int numberOfColumns, int numberOfRows, int spacing) {
            super(items, numberOfColumns, numberOfRows);
            this.itemLayoutAdapter = itemLayoutAdapter;
            mSpacing = spacing;
        }

        @Override
        protected GridListLayoutView getContentView(LayoutInflater from, ViewGroup container, int viewType) {
            return new GridListLayoutView(getContext());
        }

        @Override
        protected void bindItemView(List<T> items, GridListLayoutView layoutView) {
            super.bindItemView(items, layoutView);
            itemLayoutAdapter.setItemList(items);
            layoutView.setLayoutAdapter(itemLayoutAdapter, getNumberOfColumns(), mSpacing);
        }
    }
}
