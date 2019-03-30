package lutas.piccollage.quiz.q3

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun<T> LifecycleOwner.observe(liveData: LiveData<T>, onChange: (T?) -> Unit) {
    liveData.observe(this, Observer<T> { onChange(it) })
}