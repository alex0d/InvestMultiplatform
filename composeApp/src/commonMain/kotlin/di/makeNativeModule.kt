package di

import data.local.UserDataSource
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

typealias NativeInjectionFactory<T> = Scope.() -> T

fun makeNativeModule(
    userDataSource: NativeInjectionFactory<UserDataSource>
): Module {
    return module {
        single { userDataSource() }
    }
}