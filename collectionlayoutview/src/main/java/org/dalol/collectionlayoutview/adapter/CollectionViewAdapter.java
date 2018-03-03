package org.dalol.collectionlayoutview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.dalol.collectionlayoutview.ItemViewHolder;
import org.dalol.collectionlayoutview.callback.OnListItemClickListener;

import java.util.LinkedList;
import java.util.List;

public abstract class CollectionViewAdapter<T, VH extends ItemViewHolder<T>> {

    private final List<T> mItems = new LinkedList<>();
    private final Context mContext;
    private OnDataChangeObserver mDataChangeObserver;

    public CollectionViewAdapter(Context context) {
        mContext = context;
    }

    public void setItemList(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        if (mDataChangeObserver != null) {
            mDataChangeObserver.onChanged();
        }
    }

    public Context getContext() {
        return mContext;
    }

    public void setItemClickListener(OnListItemClickListener<T> itemClickListener) {
    }

    public int getCount() {
        return mItems.size();
    }

    public T getItemAt(int position) {
        return mItems.get(position);
    }

    public abstract VH onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    public void setOnDataChangeObserver(OnDataChangeObserver dataChangeObserver) {
        mDataChangeObserver = dataChangeObserver;
    }

    public interface OnDataChangeObserver {

        void onChanged();
    }
}