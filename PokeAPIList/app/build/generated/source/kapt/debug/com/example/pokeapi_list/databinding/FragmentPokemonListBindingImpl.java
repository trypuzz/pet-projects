package com.example.pokeapi_list.databinding;
import com.example.pokeapi_list.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPokemonListBindingImpl extends FragmentPokemonListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPokemonListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private FragmentPokemonListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            , (android.widget.ImageView) bindings[2]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerView.setTag(null);
        this.statusImage.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.viewModel == variableId) {
            setViewModel((com.example.pokeapi_list.ui.PokemonViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.example.pokeapi_list.ui.PokemonViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelPokemons((androidx.lifecycle.LiveData<java.util.List<com.example.pokeapi_list.domain.Pokemon>>) object, fieldId);
            case 1 :
                return onChangeViewModelStatus((androidx.lifecycle.LiveData<com.example.pokeapi_list.ui.PokemonApiStatus>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelPokemons(androidx.lifecycle.LiveData<java.util.List<com.example.pokeapi_list.domain.Pokemon>> ViewModelPokemons, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelStatus(androidx.lifecycle.LiveData<com.example.pokeapi_list.ui.PokemonApiStatus> ViewModelStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
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
        androidx.lifecycle.LiveData<java.util.List<com.example.pokeapi_list.domain.Pokemon>> viewModelPokemons = null;
        androidx.lifecycle.LiveData<com.example.pokeapi_list.ui.PokemonApiStatus> viewModelStatus = null;
        com.example.pokeapi_list.ui.PokemonApiStatus viewModelStatusGetValue = null;
        java.util.List<com.example.pokeapi_list.domain.Pokemon> viewModelPokemonsGetValue = null;
        com.example.pokeapi_list.ui.PokemonViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.pokemons
                        viewModelPokemons = viewModel.getPokemons();
                    }
                    updateLiveDataRegistration(0, viewModelPokemons);


                    if (viewModelPokemons != null) {
                        // read viewModel.pokemons.getValue()
                        viewModelPokemonsGetValue = viewModelPokemons.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.status
                        viewModelStatus = viewModel.getStatus();
                    }
                    updateLiveDataRegistration(1, viewModelStatus);


                    if (viewModelStatus != null) {
                        // read viewModel.status.getValue()
                        viewModelStatusGetValue = viewModelStatus.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.example.pokeapi_list.BindingAdaptersKt.bindRecyclerView(this.recyclerView, viewModelPokemonsGetValue);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            com.example.pokeapi_list.BindingAdaptersKt.bindStatus(this.statusImage, viewModelStatusGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.pokemons
        flag 1 (0x2L): viewModel.status
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}