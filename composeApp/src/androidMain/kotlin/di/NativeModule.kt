package di

import data.local.UserDataSourceImpl
import data.local.UserDataStore

val nativeModule = makeNativeModule(
    userDataSource = {
        UserDataSourceImpl(
            userDataStore = UserDataStore(get())
        )
    }
)