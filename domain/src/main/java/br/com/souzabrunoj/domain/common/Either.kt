package br.com.souzabrunoj.domain.common

typealias Error<L> = Either.Left<L>
typealias Success<R> = Either.Right<R>

sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isLeft get() = this is Left<L>
    val isRight get() = this is Right<R>

    val left: L? get() = (this as? Left<L>)?.a
    val right: R? get() = (this as? Right<R>)?.b

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    /** * Execute left block (fnL) if is Left or right block (fnR) if is Right. */
    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

    /** * Execute one block ignoring the either type. */
    fun single(fn: () -> Any): Any = fn()
}