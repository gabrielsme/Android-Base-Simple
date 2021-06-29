package digital.heylab.androidbasesimple

import android.app.Application
import com.facebook.stetho.Stetho
import digital.heylab.androidbasesimple.di.networkModule
import digital.heylab.androidbasesimple.di.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setup()
    }

    private fun setup() {
        setupKoin()
        setupStetho()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CustomApplication)
            modules(listOf(
                networkModule,
                productsModule
            ))
        }
    }
}
