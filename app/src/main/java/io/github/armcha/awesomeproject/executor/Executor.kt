package io.github.armcha.awesomeproject.executor

import java.util.concurrent.Future

interface Executor {

    fun execute(exceptionHandler: ((Throwable) -> Unit)? = {},
                   task: () -> Unit): Future<Unit>
}