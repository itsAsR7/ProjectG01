package com.example.projectv1.data


class UserDataSource {

    var name:String = ""


    private constructor() {}
    companion object {
        @Volatile
        private lateinit var instance: UserDataSource
        fun getInstance(): UserDataSource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = UserDataSource()
                }
                return instance
            }
        }
    }
}
