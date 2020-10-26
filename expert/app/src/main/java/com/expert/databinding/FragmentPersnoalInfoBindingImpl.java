package com.expert.databinding;
import com.expert.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPersnoalInfoBindingImpl extends FragmentPersnoalInfoBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tvArtistRate, 1);
        sViewsWithIds.put(R.id.ratingbar, 2);
        sViewsWithIds.put(R.id.tvRating, 3);
        sViewsWithIds.put(R.id.tvJobComplete, 4);
        sViewsWithIds.put(R.id.tvProfileComplete, 5);
        sViewsWithIds.put(R.id.ivOne, 6);
        sViewsWithIds.put(R.id.ctvbPublic, 7);
        sViewsWithIds.put(R.id.tvRate, 8);
        sViewsWithIds.put(R.id.llSwitchPublic, 9);
        sViewsWithIds.put(R.id.switchRate, 10);
        sViewsWithIds.put(R.id.tvAbout, 11);
        sViewsWithIds.put(R.id.ivEditQualification, 12);
        sViewsWithIds.put(R.id.rvQualification, 13);
        sViewsWithIds.put(R.id.inputLLName, 14);
        sViewsWithIds.put(R.id.etName, 15);
        sViewsWithIds.put(R.id.inputLLEmail, 16);
        sViewsWithIds.put(R.id.etEmail, 17);
        sViewsWithIds.put(R.id.inputLLMobileNo, 18);
        sViewsWithIds.put(R.id.etMobileNo, 19);
        sViewsWithIds.put(R.id.ll_gender_section, 20);
        sViewsWithIds.put(R.id.radioGroup, 21);
        sViewsWithIds.put(R.id.rbGenderM, 22);
        sViewsWithIds.put(R.id.rbGenderF, 23);
        sViewsWithIds.put(R.id.rbGenderO, 24);
        sViewsWithIds.put(R.id.btnUpdate, 25);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPersnoalInfoBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }
    private FragmentPersnoalInfoBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.expert.utils.CustomButton) bindings[25]
            , (com.expert.utils.CustomTextViewBold) bindings[7]
            , (com.expert.utils.CustomEditText) bindings[17]
            , (com.expert.utils.CustomEditText) bindings[19]
            , (com.expert.utils.CustomEditText) bindings[15]
            , (com.expert.utils.InputOpenFieldView) bindings[16]
            , (com.expert.utils.InputOpenFieldView) bindings[18]
            , (com.expert.utils.InputOpenFieldView) bindings[14]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.RadioGroup) bindings[21]
            , (android.widget.RatingBar) bindings[2]
            , (android.widget.RadioButton) bindings[23]
            , (android.widget.RadioButton) bindings[22]
            , (android.widget.RadioButton) bindings[24]
            , (androidx.recyclerview.widget.RecyclerView) bindings[13]
            , (com.kyleduo.switchbutton.SwitchButton) bindings[10]
            , (com.expert.utils.CustomTextView) bindings[11]
            , (com.expert.utils.CustomTextView) bindings[1]
            , (com.expert.utils.CustomTextView) bindings[4]
            , (com.expert.utils.CustomTextView) bindings[5]
            , (com.expert.utils.CustomTextView) bindings[8]
            , (com.expert.utils.CustomTextView) bindings[3]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
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