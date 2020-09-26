package hut.cwp.annotations.mvp

import kotlin.reflect.KClass

/**
 * 用于MvpActivity,MvpFragment,MvpDialogFragment子类绑定presenter
 */
@Target(AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DelegateBind(val presenter: KClass<*>)