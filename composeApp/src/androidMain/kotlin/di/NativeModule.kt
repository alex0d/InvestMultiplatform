package di

import data.local.UserDataSourceImpl

val nativeModule = makeNativeModule(
    userDataSource = { UserDataSourceImpl( get() ) }
)