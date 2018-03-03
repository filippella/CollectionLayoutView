package org.dalol.collectionlayoutview.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 23/01/2018.
 */

public abstract class BasePagerAdapter<T, V extends View> extends PagerAdapter {

    private final List<T> mModelItems = new LinkedList<>();
    private final int mTotalGridCount;
    private final int mNumberOfColumns;
    private final int mNumberOfRows;

    public BasePagerAdapter() {
        this(new LinkedList<T>(), 2, 2);
    }

    public BasePagerAdapter(@NonNull List<T> items, int numberOfColumns, int numberOfRows) {
        mModelItems.addAll(items);
        mNumberOfColumns = numberOfColumns;
        mNumberOfRows = numberOfColumns;
        mTotalGridCount = numberOfColumns * numberOfRows;
    }

    public int getNumberOfColumns() {
        return mNumberOfColumns;
    }

    public int getNumberOfRows() {
        return mNumberOfRows;
    }

    public void addItem(T item) {
        mModelItems.add(item);
        notifyDataSetChanged();
    }

    protected int getViewType(int position) {
        return -1;
    }

    public T getItem(int position) {
        return mModelItems.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        int size = mModelItems.size();

        int start = (position * mTotalGridCount);
        int endIndex = (start + mTotalGridCount);

        int end = endIndex > size ? size : endIndex;

        Context context = container.getContext().getApplicationContext();
        V view = getContentView(LayoutInflater.from(context), container, getViewType(position));

        List<T> items = new LinkedList<>();
        for(int i = start; i < end; i++) {
            items.add(mModelItems.get(i));
        }

        bindItemView(items, view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {

        int size = mModelItems.size();

        int pageCount = size / mTotalGridCount;
        pageCount += ((size - (mTotalGridCount * pageCount))  > 0) ? 1 : 0;

        return pageCount;
    }

    protected void bindItemView(List<T> item, V view) {
    }

    protected abstract V getContentView(LayoutInflater from, ViewGroup container, int viewType);
}