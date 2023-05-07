package com.example.pokeapi_list.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/example/pokeapi_list/ui/PokemonViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_pokemon", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/pokeapi_list/network/Pokemon;", "_pokemons", "", "_status", "Lcom/example/pokeapi_list/ui/PokemonApiStatus;", "pokemon", "Landroidx/lifecycle/LiveData;", "getPokemon", "()Landroidx/lifecycle/LiveData;", "pokemons", "getPokemons", "status", "getStatus", "getPokemonList", "", "onPokemonClicked", "app_debug"})
public final class PokemonViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<com.example.pokeapi_list.ui.PokemonApiStatus> _status = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.pokeapi_list.ui.PokemonApiStatus> status = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.pokeapi_list.network.Pokemon>> _pokemons = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.pokeapi_list.network.Pokemon>> pokemons = null;
    private final androidx.lifecycle.MutableLiveData<com.example.pokeapi_list.network.Pokemon> _pokemon = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.example.pokeapi_list.network.Pokemon> pokemon = null;
    
    public PokemonViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.pokeapi_list.ui.PokemonApiStatus> getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.pokeapi_list.network.Pokemon>> getPokemons() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.pokeapi_list.network.Pokemon> getPokemon() {
        return null;
    }
    
    private final void getPokemonList() {
    }
    
    public final void onPokemonClicked(@org.jetbrains.annotations.NotNull()
    com.example.pokeapi_list.network.Pokemon pokemon) {
    }
}