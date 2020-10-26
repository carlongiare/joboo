package com.expert.databinding;
import com.expert.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNewBookingsBindingImpl extends FragmentNewBookingsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlSearch, 1);
        sViewsWithIds.put(R.id.svSearch, 2);
        sViewsWithIds.put(R.id.swipeLayout, 3);
        sViewsWithIds.put(R.id.rvBooking, 4);
        sViewsWithIds.put(R.id.tvNo, 5);
        sViewsWithIds.put(R.id.tvStatus, 6);
        sViewsWithIds.put(R.id.fab, 7);
        sViewsWithIds.put(R.id.overlay, 8);
        sViewsWithIds.put(R.id.fab_sheet, 9);
        sViewsWithIds.put(R.id.tvPendings, 10);
        sViewsWithIds.put(R.id.tvAccepted, 11);
        sViewsWithIds.put(R.id.tvRejected, 12);
        sViewsWithIds.put(R.id.tvCompleted, 13);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentNewBookingsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentNewBookingsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.expert.utils.Fab) bindings[7]
            , (androidx.cardview.widget.CardView) bindings[9]
            , (com.gordonwong.materialsheetfab.DimOverlayFrameLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[1]
            , (androidx.recyclerview.widget.RecyclerView) bindings[4]
            , (androidx.appcompat.widget.SearchView) bindings[2]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[3]
            , (com.expert.utils.CustomTextView) bindings[11]
            , (com.expert.utils.CustomTextView) bindings[13]
            , (com.expert.utils.CustomTextViewBold) bindings[5]
            , (com.expert.utils.CustomTextView) bindings[10]
            , (com.expert.utils.CustomTextView) bindings[12]
            , (com.expert.utils.CustomTextView) bindings[6]
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