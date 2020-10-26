package com.expert.databinding;
import com.expert.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentArtistProfileBindingImpl extends FragmentArtistProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 1);
        sViewsWithIds.put(R.id.collapsing, 2);
        sViewsWithIds.put(R.id.ivBanner, 3);
        sViewsWithIds.put(R.id.rlOnline, 4);
        sViewsWithIds.put(R.id.tvOnOff, 5);
        sViewsWithIds.put(R.id.swOnOff, 6);
        sViewsWithIds.put(R.id.ivEditPersonal, 7);
        sViewsWithIds.put(R.id.header, 8);
        sViewsWithIds.put(R.id.titleContainer, 9);
        sViewsWithIds.put(R.id.llImg, 10);
        sViewsWithIds.put(R.id.btnDelete, 11);
        sViewsWithIds.put(R.id.btnChange, 12);
        sViewsWithIds.put(R.id.tvName, 13);
        sViewsWithIds.put(R.id.tvWork, 14);
        sViewsWithIds.put(R.id.llLocation, 15);
        sViewsWithIds.put(R.id.tvLocation, 16);
        sViewsWithIds.put(R.id.tvBio, 17);
        sViewsWithIds.put(R.id.tabLayout, 18);
        sViewsWithIds.put(R.id.pager, 19);
        sViewsWithIds.put(R.id.ivArtist, 20);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentArtistProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private FragmentArtistProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[1]
            , (com.expert.utils.CustomButton) bindings[12]
            , (com.expert.utils.CustomButton) bindings[11]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[2]
            , (android.widget.RelativeLayout) bindings[8]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[20]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[15]
            , (androidx.viewpager.widget.ViewPager) bindings[19]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.Switch) bindings[6]
            , (com.google.android.material.tabs.TabLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[9]
            , (com.expert.utils.CustomTextView) bindings[17]
            , (com.expert.utils.CustomTextView) bindings[16]
            , (com.expert.utils.CustomTextViewBold) bindings[13]
            , (com.expert.utils.CustomTextViewBold) bindings[5]
            , (com.expert.utils.CustomTextView) bindings[14]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
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