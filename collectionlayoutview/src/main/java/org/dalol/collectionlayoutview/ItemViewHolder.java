package org.dalol.collectionlayoutview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Workspace on 25/01/2018.
 */

public class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    protected ItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(T data) {

    }
}
