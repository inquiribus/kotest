package io.kotest.core.listeners

import io.kotest.core.spec.Spec

interface AfterSpecListener : Listener {

   /**
    * Is invoked after the [TestCase]s that are part of a particular
    * [Spec] instance have completed.
    *
    * If a spec is instantiated multiple times - for example, if
    * [InstancePerTest] or [InstancePerLeaf] isolation modes are used,
    * then this callback will be invoked for each instantiated spec,
    * after the tests that are applicable to that spec instance have
    * returned.
    *
    * This callback should be used if you need to perform cleanup
    * after each individual spec instance. If you simply need to
    * perform cleanup once per class file, then use [finalizeSpec].
    *
    * @param spec the [Spec] instance.
    */
   suspend fun afterSpec(spec: Spec): Unit = Unit
}
