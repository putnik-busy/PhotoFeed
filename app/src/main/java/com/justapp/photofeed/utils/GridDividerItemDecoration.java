package com.justapp.photofeed.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Добавляет внутренние разделители к {@link RecyclerView}  с помощью {@link GridLayoutManager}
 *
 * @author Sergey Rodionov
 */
public final class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = {android.R.attr.listDivider};
    private Drawable mDivider;
    private int mNumColumns;

    /**
     * Конструктор для {@link GridDividerItemDecoration}
     *
     * @param context    контекст
     * @param numColumns количество колонок
     */
    public GridDividerItemDecoration(@NonNull Context context, @IntegerRes int numColumns) {
        this(context, numColumns, 0);
    }

    /**
     * Конструктор для {@link GridDividerItemDecoration}
     *
     * @param context    контекст
     * @param numColumns количество колонок
     * @param resId      ресурс разделителя
     */
    public GridDividerItemDecoration(@NonNull Context context, @IntegerRes int numColumns, @DrawableRes int resId) {

        if (resId == 0) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            mDivider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        } else {
            mDivider = ContextCompat.getDrawable(context, resId);
        }

        mNumColumns = numColumns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        drawVertical(canvas, parent);
        drawHorizontalDividers(canvas, parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        boolean childIsInLeftmostColumn = (parent.getChildAdapterPosition(view) % mNumColumns) == 0;
        if (!childIsInLeftmostColumn) {
            outRect.left = mDivider.getIntrinsicWidth();
        }

        boolean childIsInFirstRow = (parent.getChildAdapterPosition(view)) < mNumColumns;
        if (!childIsInFirstRow) {
            outRect.top = mDivider.getIntrinsicHeight();
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        if (parent.getChildCount() == 0) {
            return;
        }

        final int childCount = parent.getChildCount();

        for (int i = 1; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontalDividers(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}
