package com.example.pokeapi_list;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.pokeapi_list.databinding.FragmentPokemonDetailBindingImpl;
import com.example.pokeapi_list.databinding.FragmentPokemonListBindingImpl;
import com.example.pokeapi_list.databinding.ListViewItemBindingImpl;
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
  private static final int LAYOUT_FRAGMENTPOKEMONDETAIL = 1;

  private static final int LAYOUT_FRAGMENTPOKEMONLIST = 2;

  private static final int LAYOUT_LISTVIEWITEM = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.pokeapi_list.R.layout.fragment_pokemon_detail, LAYOUT_FRAGMENTPOKEMONDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.pokeapi_list.R.layout.fragment_pokemon_list, LAYOUT_FRAGMENTPOKEMONLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.pokeapi_list.R.layout.list_view_item, LAYOUT_LISTVIEWITEM);
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
        case  LAYOUT_FRAGMENTPOKEMONDETAIL: {
          if ("layout/fragment_pokemon_detail_0".equals(tag)) {
            return new FragmentPokemonDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_pokemon_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPOKEMONLIST: {
          if ("layout/fragment_pokemon_list_0".equals(tag)) {
            return new FragmentPokemonListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_pokemon_list is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTVIEWITEM: {
          if ("layout/list_view_item_0".equals(tag)) {
            return new ListViewItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_view_item is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "clickListener");
      sKeys.put(2, "pokemon");
      sKeys.put(3, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/fragment_pokemon_detail_0", com.example.pokeapi_list.R.layout.fragment_pokemon_detail);
      sKeys.put("layout/fragment_pokemon_list_0", com.example.pokeapi_list.R.layout.fragment_pokemon_list);
      sKeys.put("layout/list_view_item_0", com.example.pokeapi_list.R.layout.list_view_item);
    }
  }
}
