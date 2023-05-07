package com.example.pokeapi_list.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/pokeapi_list/ui/PokemonListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/pokeapi_list/network/Pokemon;", "Lcom/example/pokeapi_list/ui/PokemonListAdapter$PokemonViewHolder;", "clickListener", "Lcom/example/pokeapi_list/ui/PokemonListener;", "(Lcom/example/pokeapi_list/ui/PokemonListener;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "PokemonViewHolder", "app_debug"})
public final class PokemonListAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.pokeapi_list.network.Pokemon, com.example.pokeapi_list.ui.PokemonListAdapter.PokemonViewHolder> {
    private final com.example.pokeapi_list.ui.PokemonListener clickListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.pokeapi_list.ui.PokemonListAdapter.DiffCallback DiffCallback = null;
    
    public PokemonListAdapter(@org.jetbrains.annotations.NotNull()
    com.example.pokeapi_list.ui.PokemonListener clickListener) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.pokeapi_list.ui.PokemonListAdapter.PokemonViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.pokeapi_list.ui.PokemonListAdapter.PokemonViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/example/pokeapi_list/ui/PokemonListAdapter$PokemonViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/pokeapi_list/databinding/ListViewItemBinding;", "(Lcom/example/pokeapi_list/databinding/ListViewItemBinding;)V", "getBinding", "()Lcom/example/pokeapi_list/databinding/ListViewItemBinding;", "setBinding", "bind", "", "clickListener", "Lcom/example/pokeapi_list/ui/PokemonListener;", "pokemon", "Lcom/example/pokeapi_list/network/Pokemon;", "app_debug"})
    public static final class PokemonViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private com.example.pokeapi_list.databinding.ListViewItemBinding binding;
        
        public PokemonViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.databinding.ListViewItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.pokeapi_list.databinding.ListViewItemBinding getBinding() {
            return null;
        }
        
        public final void setBinding(@org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.databinding.ListViewItemBinding p0) {
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.ui.PokemonListener clickListener, @org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.network.Pokemon pokemon) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/pokeapi_list/ui/PokemonListAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/pokeapi_list/network/Pokemon;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.pokeapi_list.network.Pokemon> {
        
        private DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.network.Pokemon oldItem, @org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.network.Pokemon newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.network.Pokemon oldItem, @org.jetbrains.annotations.NotNull()
        com.example.pokeapi_list.network.Pokemon newItem) {
            return false;
        }
    }
}