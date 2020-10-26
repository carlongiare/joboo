package com.expert;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.expert.databinding.ActivityViewInvoiceBindingImpl;
import com.expert.databinding.AdapterAllBookingsBindingImpl;
import com.expert.databinding.DailogArGallryBindingImpl;
import com.expert.databinding.DailogArProductBindingImpl;
import com.expert.databinding.DailogArQualificationBindingImpl;
import com.expert.databinding.FragmentArtistProfileBindingImpl;
import com.expert.databinding.FragmentNewBookingsBindingImpl;
import com.expert.databinding.FragmentPersnoalInfoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYVIEWINVOICE = 1;

  private static final int LAYOUT_ADAPTERALLBOOKINGS = 2;

  private static final int LAYOUT_DAILOGARGALLRY = 3;

  private static final int LAYOUT_DAILOGARPRODUCT = 4;

  private static final int LAYOUT_DAILOGARQUALIFICATION = 5;

  private static final int LAYOUT_FRAGMENTARTISTPROFILE = 6;

  private static final int LAYOUT_FRAGMENTNEWBOOKINGS = 7;

  private static final int LAYOUT_FRAGMENTPERSNOALINFO = 8;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.activity_view_invoice, LAYOUT_ACTIVITYVIEWINVOICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.adapter_all_bookings, LAYOUT_ADAPTERALLBOOKINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.dailog_ar_gallry, LAYOUT_DAILOGARGALLRY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.dailog_ar_product, LAYOUT_DAILOGARPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.dailog_ar_qualification, LAYOUT_DAILOGARQUALIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.fragment_artist_profile, LAYOUT_FRAGMENTARTISTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.fragment_new_bookings, LAYOUT_FRAGMENTNEWBOOKINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.expert.R.layout.fragment_persnoal_info, LAYOUT_FRAGMENTPERSNOALINFO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYVIEWINVOICE: {
          if ("layout/activity_view_invoice_0".equals(tag)) {
            return new ActivityViewInvoiceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_view_invoice is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERALLBOOKINGS: {
          if ("layout/adapter_all_bookings_0".equals(tag)) {
            return new AdapterAllBookingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_all_bookings is invalid. Received: " + tag);
        }
        case  LAYOUT_DAILOGARGALLRY: {
          if ("layout/dailog_ar_gallry_0".equals(tag)) {
            return new DailogArGallryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dailog_ar_gallry is invalid. Received: " + tag);
        }
        case  LAYOUT_DAILOGARPRODUCT: {
          if ("layout/dailog_ar_product_0".equals(tag)) {
            return new DailogArProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dailog_ar_product is invalid. Received: " + tag);
        }
        case  LAYOUT_DAILOGARQUALIFICATION: {
          if ("layout/dailog_ar_qualification_0".equals(tag)) {
            return new DailogArQualificationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dailog_ar_qualification is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTARTISTPROFILE: {
          if ("layout/fragment_artist_profile_0".equals(tag)) {
            return new FragmentArtistProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_artist_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNEWBOOKINGS: {
          if ("layout/fragment_new_bookings_0".equals(tag)) {
            return new FragmentNewBookingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_new_bookings is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSNOALINFO: {
          if ("layout/fragment_persnoal_info_0".equals(tag)) {
            return new FragmentPersnoalInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_persnoal_info is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(8);

    static {
      sKeys.put("layout/activity_view_invoice_0", com.expert.R.layout.activity_view_invoice);
      sKeys.put("layout/adapter_all_bookings_0", com.expert.R.layout.adapter_all_bookings);
      sKeys.put("layout/dailog_ar_gallry_0", com.expert.R.layout.dailog_ar_gallry);
      sKeys.put("layout/dailog_ar_product_0", com.expert.R.layout.dailog_ar_product);
      sKeys.put("layout/dailog_ar_qualification_0", com.expert.R.layout.dailog_ar_qualification);
      sKeys.put("layout/fragment_artist_profile_0", com.expert.R.layout.fragment_artist_profile);
      sKeys.put("layout/fragment_new_bookings_0", com.expert.R.layout.fragment_new_bookings);
      sKeys.put("layout/fragment_persnoal_info_0", com.expert.R.layout.fragment_persnoal_info);
    }
  }
}
