package ru.vvpanf.counter.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.vvpanf.counter.dao.CounterDao
import ru.vvpanf.counter.db.CounterDatabase
import ru.vvpanf.counter.viewmodel.CounterViewModel
import ru.vvpanf.counter.viewmodel.MainViewModel

val appModule = module {
    single<CounterDatabase> {
        Room.databaseBuilder(
            get(),
            CounterDatabase::class.java,
            "counter-db"
        )
        .build()
    }

    single<CounterDao> {
        get<CounterDatabase>().counterDao()
    }

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        CounterViewModel(get())
    }
}