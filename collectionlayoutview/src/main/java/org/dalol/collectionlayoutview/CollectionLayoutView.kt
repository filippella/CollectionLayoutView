package org.dalol.collectionlayoutview

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.FrameLayout
import org.dalol.collectionlayoutview.adapter.CollectionViewAdapter
import org.dalol.collectionlayoutview.views.GridListLayoutView
import org.dalol.collectionlayoutview.views.PagerLayoutView

class CollectionLayoutView : FrameLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        initialize(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context, attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs)
    }

    private fun initialize(context: Context, attrs: AttributeSet?) {
        mContext = context
    }

    fun <T, VH : ItemViewHolder<T>> setCollectionAsList(itemLayoutAdapter : CollectionViewAdapter<T, VH>, items: List<T>, numOfCols: Int, spacing: Int) {
        removeAllViews()
        val layoutView = GridListLayoutView(mContext)
        layoutView.setLayoutAdapter(itemLayoutAdapter, numOfCols, spacing)
        addView(layoutView)
    }

    fun <T, VH : ItemViewHolder<T>> setCollectionAsPager(itemLayoutAdapter : CollectionViewAdapter<T, VH>, items: List<T>, numOfCols: Int, numOfRows: Int, spacing: Int) {
        removeAllViews()
        val pagerLayoutView = PagerLayoutView(mContext!!)
        pagerLayoutView.setLayoutAdapter(itemLayoutAdapter, items, numOfCols, numOfRows, spacing)
        addView(pagerLayoutView)
    }
}
