package com.gian.recipesmaker.di


//@Module
//@InstallIn(ApplicationComponent::class) // para proveer las dependencias a nivel de app
abstract class AppModule {

    /*@Binds //Para bindear solo interfaces
    abstract fun bindLoginActivity(activity:LoginActivity):LoginView //podemos usar esta dependencia en cualquier activity o clase que marquemos como android entry point
    @Binds
    abstract fun bindLoginPresenter(impl:LoginPresenter):LoginInteractor.onLoginFinishedListener


    @InstallIn(ActivityComponent::class)
    @Module
    object MainActivityModule {

        @Provides
        fun bindActivity(activity: Activity): LoginActivity {
            return activity as LoginActivity
        }

    }

     */
}