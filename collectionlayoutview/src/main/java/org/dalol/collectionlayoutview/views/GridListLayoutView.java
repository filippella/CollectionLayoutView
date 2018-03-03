package org.dalol.collectionlayoutview.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.dalol.collectionlayoutview.ItemViewHolder;
import org.dalol.collectionlayoutview.adapter.CollectionViewAdapter;
import org.dalol.collectionlayoutview.decorator.GridItemsMarginDecorator;

/**
 * Created by filippo on 23/01/2018.
 */

public class GridListLayoutView extends RecyclerView {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public GridListLayoutView(Context context) {
        super(context);
        initialize(context, null);
    }

    public GridListLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public GridListLayoutView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        setHasFixedSize(true);
        setClipToPadding(false);
    }

    public <T, VH  extends ItemViewHolder<T>> void setLayoutAdapter(CollectionViewAdapter<T, VH> itemLayoutAdapter, int numberOfColumns, int spacing) {
        setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        ListLayoutAdapter<T, VH> layoutAdapter = new ListLayoutAdapter<>(itemLayoutAdapter);
        itemLayoutAdapter.setOnDataChangeObserver(layoutAdapter);
        setAdapter(layoutAdapter);
        addItemDecoration(new GridItemsMarginDecorator(spacing, numberOfColumns));
    }

    private class ListLayoutAdapter<T, VH  extends ItemViewHolder<T>> extends RecyclerView.Adapter<VH> implements CollectionViewAdapter.OnDataChangeObserver {

        private final CollectionViewAdapter<T, VH> mItemLayoutAdapter;

        ListLayoutAdapter(CollectionViewAdapter<T, VH> itemLayoutAdapter) {
            mItemLayoutAdapter = itemLayoutAdapter;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return mItemLayoutAdapter.onCreateViewHolder(mLayoutInflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.bind(mItemLayoutAdapter.getItemAt(position));
        }

        @Override
        public int getItemCount() {
            return mItemLayoutAdapter.getCount();
        }

        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }
    }
}
