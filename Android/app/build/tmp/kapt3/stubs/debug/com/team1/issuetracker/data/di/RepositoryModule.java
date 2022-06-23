package com.team1.issuetracker.data.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lcom/team1/issuetracker/data/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/team1/issuetracker/data/repository/LoginRepository;", "authRepositoryImpl", "Lcom/team1/issuetracker/data/repository/LoginRepositoryImpl;", "bindLabelRepository", "Lcom/team1/issuetracker/data/repository/LabelRepository;", "labelRepositoryImpl", "Lcom/team1/issuetracker/data/repository/LabelRepositoryImpl;", "bindMilestoneRepository", "Lcom/team1/issuetracker/data/repository/MilestoneRepository;", "milestoneRepositoryImpl", "Lcom/team1/issuetracker/data/repository/MilestoneRepositoryImpl;", "app_debug"})
@dagger.Module()
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    @javax.inject.Singleton()
    public abstract com.team1.issuetracker.data.repository.LoginRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.team1.issuetracker.data.repository.LoginRepositoryImpl authRepositoryImpl);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    @javax.inject.Singleton()
    public abstract com.team1.issuetracker.data.repository.LabelRepository bindLabelRepository(@org.jetbrains.annotations.NotNull()
    com.team1.issuetracker.data.repository.LabelRepositoryImpl labelRepositoryImpl);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    @javax.inject.Singleton()
    public abstract com.team1.issuetracker.data.repository.MilestoneRepository bindMilestoneRepository(@org.jetbrains.annotations.NotNull()
    com.team1.issuetracker.data.repository.MilestoneRepositoryImpl milestoneRepositoryImpl);
}