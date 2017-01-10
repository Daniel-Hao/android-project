package com.daniel.hao.corelib.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hao.daniel.corelib.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowLayout extends ViewGroup {
    private int mVerticalSpacing = 10; // 每个item纵向间距
    private int mHorizontalSpacing = 30; // 每个item横向间距
    private int textDefautColor = ContextCompat.getColor(getContext(),R.color.clr_3E3A39);// 字体默认颜色
    private int textFocusColor = ContextCompat.getColor(getContext(), R.color.clr_FFFFFF);// 字体选中颜色
    private int textSize = 14;// 字体大小 （单位是dp为单位）
    private int textPading = 14;// 字体pading（单位是dp为单位）
    private int texttoppading = 6;
    private int backgroundDefalut = R.drawable.shape_time_bg_gray;// 默认背景
    private int backgroundFocus = R.drawable.shape_time_bg;// 选中背景


    private int[] lists;// 所有内容
    private ArrayList<FlowTag> flowTags = new ArrayList<FlowTag>();// 所有标签

    private boolean canChange = false;//表示是否可以改变状态

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHorizontalSpacing(int pixelSize) {
        mHorizontalSpacing = pixelSize;
    }

    public void setVerticalSpacing(int pixelSize) {
        mVerticalSpacing = pixelSize;
    }

    public void setBackgroundDefalut(int backgroundDefalut) {
        this.backgroundDefalut = backgroundDefalut;
    }

    public void setBackgroundFocus(int backgroundFocus) {
        this.backgroundFocus = backgroundFocus;
    }

    public void setTextDefaultColor(int textColor) {
        this.textDefautColor = textColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int selfWidth = resolveSize(0, widthMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;

        // 通过计算每一个子控件的高度，得到自己的高度
        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();

            childView.measure(
                    getChildMeasureSpec(widthMeasureSpec, paddingLeft
                            + paddingRight, childLayoutParams.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop
                            + paddingBottom, childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > selfWidth) {
                //childLeft = paddingLeft;
                childLeft = paddingLeft + childWidth;
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            } else {
                childLeft += childWidth + mHorizontalSpacing;
            }
        }

        int wantedHeight = childTop + lineHeight + paddingBottom;
        setMeasuredDimension(selfWidth,
                resolveSize(wantedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        // 根据子控件的宽高，计算子控件应该出现的位置。
        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }
            childView.layout(childLeft, childTop, childLeft + childWidth,
                    childTop + childHeight);
            childLeft += childWidth + mHorizontalSpacing;
        }
    }

    //获取所有
    public ArrayList<FlowTag> getFlowTags() {
        return flowTags;
    }

    public FlowTag getFlowTagByIndex(int index) {
        if (!flowTags.isEmpty() && index < flowTags.size() && index >= 0) {
            return flowTags.get(index);
        }
        return null;
    }

    public void setSelectByPosition(int index) {
        if (!flowTags.isEmpty() && index < flowTags.size() && index >= 0) {
            int num = getChildCount();
            if (num > 0) {
                if (canChange) {
                    for (int i = 0; i < num; i++) {
                        View v = getChildAt(i);
                        if (i == index) {
                            if (flowTags.get(i).isSelect) {
                                flowTags.get(i).setSelect(false);
                                v.setBackgroundResource(backgroundDefalut);
                                if (v instanceof TextView) {
                                    ((TextView) v).setTextColor(textDefautColor);
                                }
                            } else {
                                flowTags.get(i).setSelect(true);
                                v.setBackgroundResource(backgroundFocus);
                                if (v instanceof TextView) {
                                    ((TextView) v).setTextColor(textFocusColor);
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < num; i++) {
                        View v = getChildAt(i);
                        if (i == index) {
                            flowTags.get(i).setSelect(true);
                            v.setBackgroundResource(backgroundFocus);
                            if (v instanceof TextView) {
                                ((TextView) v).setTextColor(textFocusColor);
                            }
                        } else {
                            flowTags.get(i).setSelect(false);
                            v.setBackgroundResource(backgroundDefalut);
                            if (v instanceof TextView) {
                                ((TextView) v).setTextColor(textDefautColor);
                            }
                        }
                    }
                }
            }

        } else if (!flowTags.isEmpty()) {
            //清空所有状态
            int num = getChildCount();
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    View v = getChildAt(i);
                    flowTags.get(i).setSelect(false);
                    v.setBackgroundResource(backgroundDefalut);
                    if (v instanceof TextView) {
                        ((TextView) v).setTextColor(textDefautColor);
                    }
                }
            }
        }

    }

    public ArrayList<FlowTag> getSelectFlowTags() {
        if (!flowTags.isEmpty()) {
            ArrayList<FlowTag> tags = new ArrayList<FlowTag>();
            for (FlowTag tag : flowTags) {
                if (tag.isSelect()) {
                    tags.add(tag);
                }
            }
            return tags;
        }
        return null;
    }

    public boolean isSelectByPosition(int position) {

        ArrayList<FlowTag> tags = getFlowTags();
        if (tags != null && tags.size() > 0) {
            if (tags.get(position).isSelect) {
                return true;
            }
        }
        return false;
    }

    public int[] getLists() {
        return lists;
    }

    public void setLists(int[] lists, ISelectionChangedCallback iSelectionChangedCallback) {
        this.lists = lists;
        // 添加布局
        if (lists == null || lists.length <= 0) {
            return;
        }
        for (int i = 0; i < lists.length; i++) {
            FlowTag tag = new FlowTag(i, getResources().getString(lists[i]));
            flowTags.add(tag);
            addTag(tag, iSelectionChangedCallback);
        }
    }

    public void setLists(String[] lists, ISelectionChangedCallback iSelectionChangedCallback) {
        // 添加布局
        if (lists == null || lists.length <= 0) {
            return;
        }
        for (int i = 0; i < lists.length; i++) {
            FlowTag tag = new FlowTag(i, lists[i]);
            flowTags.add(tag);
            addTag(tag, iSelectionChangedCallback);

        }
    }

    public void setLists(List<String> lists, ISelectionChangedCallback iSelectionChangedCallback) {
        // 添加布局
        if (lists == null || lists.size() <= 0) {
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            FlowTag tag = new FlowTag(i, lists.get(i));
            flowTags.add(tag);
            addTag(tag, iSelectionChangedCallback);

        }
    }

    public void setLists(List<String> lists) {
        // 添加布局
        if (lists == null || lists.size() <= 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < lists.size(); i++) {
            FlowTag tag = new FlowTag(i, lists.get(i));
            flowTags.add(tag);
            addTag(tag);
        }
    }

    /**
     * Tag Class
     */
    public class FlowTag {

        private int index;
        private String text;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean isSelect) {
            this.isSelect = isSelect;
        }

        private Map<?, ?> attrs;

        public FlowTag(int index, String text) {
            this.index = index;
            this.text = text;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Map<?, ?> getAttrs() {
            return attrs;
        }

        public void setAttrs(Map<?, ?> attrs) {
            this.attrs = attrs;
        }
    }

    public void setSelects() {
        int num = getChildCount();
        if (num > 0) {
            if (canChange) {
                for (int i = 0; i < num; i++) {
                    View v = getChildAt(i);
                    if (flowTags.get(i).isSelect) {
                        v.setBackgroundResource(backgroundFocus);
                        if (v instanceof TextView) {
                            ((TextView) v).setTextColor(textFocusColor);
                        }
                    } else {
                        v.setBackgroundResource(backgroundDefalut);
                        if (v instanceof TextView) {
                            ((TextView) v).setTextColor(textDefautColor);
                        }
                    }
                }
            }
        }
    }

    /**
     * //添加布局
     *
     * @param tag
     * @Title: addTag
     * @Description: TODO
     * @return: void
     */
    private void addTag(final FlowTag tag, final ISelectionChangedCallback iSelectionChangedCallback) {
        final TextView view = new TextView(getContext());
        view.setText(tag.getText());
        view.setTextSize(textSize);
        view.setPadding(dp2px(getContext(), textPading),
                dp2px(getContext(), texttoppading),
                dp2px(getContext(), textPading),
                dp2px(getContext(), texttoppading));
        if (tag.isSelect) {
            view.setBackgroundResource(backgroundFocus);
            view.setTextColor(textFocusColor);
        } else {
            view.setBackgroundResource(backgroundDefalut);
            view.setTextColor(textDefautColor);
        }
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setSelectByPosition(tag.getIndex());

                if (iSelectionChangedCallback != null) {
                    iSelectionChangedCallback.callBack(tag.getIndex());
                }
            }
        });
        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        addView(view, lp);
    }

    private void addTag(final FlowTag tag) {
        final TextView view = new TextView(getContext());
        view.setText(tag.getText());
        view.setTextSize(textSize);
        view.setPadding(dp2px(getContext(), textPading),
                dp2px(getContext(), texttoppading),
                dp2px(getContext(), textPading),
                dp2px(getContext(), texttoppading));
        if (tag.isSelect) {
            view.setBackgroundResource(backgroundFocus);
            view.setTextColor(textFocusColor);
        } else {
            view.setBackgroundResource(backgroundDefalut);
            view.setTextColor(textDefautColor);
        }
        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        addView(view, lp);
    }

    /**
     * dp转px
     *
     * @param context
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public interface ISelectionChangedCallback {
        public void callBack(int position);
    }
}