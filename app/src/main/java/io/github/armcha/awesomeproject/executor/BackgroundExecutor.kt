package io.github.armcha.awesomeproject.executor

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

object BackgroundExecutor : Executor {

    private var executor: ExecutorService =
            Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())

    private fun <T> submit(task: () -> T): Future<T> {
        return executor.submit(task)
    }

    override fun execute(exceptionHandler: ((Throwable) -> Unit)?,
                         task: () -> Unit): Future<Unit> {
        return submit {
            return@submit try {
                task()
            } catch (thr: Throwable) {
                val result = exceptionHandler?.invoke(thr)
                if (result != null) {
                    result
                } else {
                    Unit
                }
            }
        }
    }
}