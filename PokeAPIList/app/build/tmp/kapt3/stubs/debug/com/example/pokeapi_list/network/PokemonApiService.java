package com.example.pokeapi_list.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006"}, d2 = {"Lcom/example/pokeapi_list/network/PokemonApiService;", "", "getData", "", "Lcom/example/pokeapi_list/network/Pokemon;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PokemonApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "android-basics-kotlin-unit-4-pathway-2-project-api.json")
    public abstract java.lang.Object getData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.pokeapi_list.network.Pokemon>> continuation);
}