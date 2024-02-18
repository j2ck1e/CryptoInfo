package com.jcdesign.cryptoinfo.di

import com.jcdesign.cryptoinfo.workers.ChildWorkerFactory
import com.jcdesign.cryptoinfo.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}

