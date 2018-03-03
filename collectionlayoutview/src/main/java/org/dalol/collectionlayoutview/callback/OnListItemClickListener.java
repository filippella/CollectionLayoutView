package org.dalol.collectionlayoutview.callback;


import android.view.View;

public interface OnListItemClickListener<T> {

    void onItemClick(View view, T item, int position);
}
