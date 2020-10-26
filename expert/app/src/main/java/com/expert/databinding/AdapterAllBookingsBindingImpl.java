package com.expert.databinding;
import com.expert.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdapterAllBookingsBindingImpl extends AdapterAllBookingsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cardData, 1);
        sViewsWithIds.put(R.id.tvTxt, 2);
        sViewsWithIds.put(R.id.ivArtist, 3);
        sViewsWithIds.put(R.id.tvName, 4);
        sViewsWithIds.put(R.id.llDate, 5);
        sViewsWithIds.put(R.id.tvDate, 6);
        sViewsWithIds.put(R.id.llLocation, 7);
        sViewsWithIds.put(R.id.tvLocation, 8);
        sViewsWithIds.put(R.id.llDescription, 9);
        sViewsWithIds.put(R.id.tvDescription, 10);
        sViewsWithIds.put(R.id.llACDE, 11);
        sViewsWithIds.put(R.id.llAccept, 12);
        sViewsWithIds.put(R.id.llDecline, 13);
        sViewsWithIds.put(R.id.llSt, 14);
        sViewsWithIds.put(R.id.llStart, 15);
        sViewsWithIds.put(R.id.llCancel, 16);
        sViewsWithIds.put(R.id.tvCompleted, 17);
        sViewsWithIds.put(R.id.tvRejected, 18);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdapterAllBookingsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private AdapterAllBookingsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[1]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[3]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[15]
            , (com.expert.utils.CustomTextViewBold) bindings[17]
            , (com.expert.utils.CustomTextView) bindings[6]
            , (com.expert.utils.CustomTextView) bindings[10]
            , (com.expert.utils.CustomTextView) bindings[8]
            , (com.expert.utils.CustomTextViewBold) bindings[4]
            , (com.expert.utils.CustomTextViewBold) bindings[18]
            , (com.expert.utils.CustomTextView) bindings[2]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}