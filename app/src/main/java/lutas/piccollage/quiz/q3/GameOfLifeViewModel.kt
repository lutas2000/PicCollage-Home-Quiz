package lutas.piccollage.quiz.q3

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class GameOfLifeViewModel(tempWorld: World? = null): ViewModel() {

    class Factory: ViewModelProvider.Factory {
        private var world: World? = null

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameOfLifeViewModel(world) as T
        }
    }

    private var timerDisposable: Disposable? = null
    private val _world = MutableLiveData<World>()
    val world: LiveData<World>
        get() = _world
    private val _isRunning = MutableLiveData<Boolean>()
    val isRunning: LiveData<Boolean>
        get() = _isRunning

    init {
        _world.value = tempWorld
    }

    fun setWorld(width: Int, height: Int) {
        // TODO handle Screen changed
        if (_world.value == null) {
            _world.value = World(width, height)
        }
    }

    fun addLife(x: Int, y: Int) {
        try {
            _world.value?.get(x, y)?.born()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    fun start() {
        _isRunning.value = true
        timerDisposable = Observable
            .interval(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe { _world.value?.nextGeneration() }
    }

    fun stop() {
        _isRunning.value = false
        timerDisposable?.dispose()
    }
}