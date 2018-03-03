package org.dalol.demo;

import android.view.View;
import android.widget.TextView;

import org.dalol.collectionlayoutview.ItemViewHolder;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since Saturday, 03/03/2018 at 18:19.
 */
public class SingleItemViewHolder extends ItemViewHolder<String> {

    final TextView textView;

    SingleItemViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    @Override
    public void bind(String data) {
        super.bind(data);
        textView.setText(data);
    }
}
