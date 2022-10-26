package digital.heylab.androidbasesimple.base

interface Mapper<T : Any, Model : Any> {
    fun map(model: Model): T
}