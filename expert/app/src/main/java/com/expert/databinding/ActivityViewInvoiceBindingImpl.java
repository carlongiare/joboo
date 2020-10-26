package com.expert.databinding;
import com.expert.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityViewInvoiceBindingImpl extends ActivityViewInvoiceBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivCross, 1);
        sViewsWithIds.put(R.id.tvInvoiceId, 2);
        sViewsWithIds.put(R.id.ivProfile, 3);
        sViewsWithIds.put(R.id.tvName, 4);
        sViewsWithIds.put(R.id.tvWork, 5);
        sViewsWithIds.put(R.id.view1, 6);
        sViewsWithIds.put(R.id.tvInvoiceDate, 7);
        sViewsWithIds.put(R.id.tvInvoiceDate1, 8);
        sViewsWithIds.put(R.id.tvServiceType, 9);
        sViewsWithIds.put(R.id.tvPrice, 10);
        sViewsWithIds.put(R.id.view2, 11);
        sViewsWithIds.put(R.id.tvSubtotal, 12);
        sViewsWithIds.put(R.id.view3, 13);
        sViewsWithIds.put(R.id.tvDiscount, 14);
        sViewsWithIds.put(R.id.view4, 15);
        sViewsWithIds.put(R.id.tvTotal, 16);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityViewInvoiceBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private ActivityViewInvoiceBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[3]
            , (com.expert.utils.CustomTextView) bindings[14]
            , (com.expert.utils.CustomTextView) bindings[7]
            , (com.expert.utils.CustomTextView) bindings[8]
            , (com.expert.utils.CustomTextView) bindings[2]
            , (com.expert.utils.CustomTextView) bindings[4]
            , (com.expert.utils.CustomTextView) bindings[10]
            , (com.expert.utils.CustomTextView) bindings[9]
            , (com.expert.utils.CustomTextView) bindings[12]
            , (com.expert.utils.CustomTextView) bindings[16]
            , (com.expert.utils.CustomTextView) bindings[5]
            , (android.view.View) bindings[6]
            , (android.view.View) bindings[11]
            , (android.view.View) bindings[13]
            , (android.view.View) bindings[15]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
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