/*
 * Copyright (c) 2022 WallPanel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.wallpanel.app

import android.content.Context
import androidx.multidex.MultiDex
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import xyz.wallpanel.app.BuildConfig
import xyz.wallpanel.app.di.DaggerApplicationComponent
import xyz.wallpanel.app.utils.LauncherShortcuts
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class WallPanel : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        LauncherShortcuts.createShortcuts(this)
        FirebaseApp.initializeApp(applicationContext)
        FirebaseAnalytics.getInstance(applicationContext)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}